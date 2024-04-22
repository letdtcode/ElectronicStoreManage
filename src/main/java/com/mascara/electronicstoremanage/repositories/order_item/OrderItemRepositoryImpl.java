package com.mascara.electronicstoremanage.repositories.order_item;

import com.mascara.electronicstoremanage.common.mapper.OrderItemMapper;
import com.mascara.electronicstoremanage.entities.Feature;
import com.mascara.electronicstoremanage.entities.Order;
import com.mascara.electronicstoremanage.entities.OrderItem;
import com.mascara.electronicstoremanage.entities.Product;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemCreateRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemUpdateRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemViewModel;
import com.mascara.electronicstoremanage.view_model.sale.CardItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.sale.CardItemViewModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : OrderItemRepositoryImpl
 */
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private static OrderItemRepositoryImpl instance = null;

    public static OrderItemRepositoryImpl getInstance() {
        if (instance == null)
            instance = new OrderItemRepositoryImpl();
        return instance;
    }

    private OrderItemRepositoryImpl() {

    }

    @Override
    public Long insert(OrderItemCreateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Long orderItemId = -1L;
        try {
            tx = session.beginTransaction();
            Optional<Order> orderOptional = session.createQuery("select o from Order o where o.id =: id", Order.class)
                    .setParameter("id", request.getOrderId()).uniqueResultOptional();
            Optional<Product> productOptional = session.createQuery("select p from Product p where p.id =: id", Product.class)
                    .setParameter("id", request.getProductId()).uniqueResultOptional();
            Optional<OrderItem> orderItemOptional = session.createQuery("select oi from OrderItem oi where oi.productId =: productId and oi.orderId =: orderId", OrderItem.class)
                    .setParameter("productId", request.getProductId())
                    .setParameter("orderId", request.getOrderId())
                    .uniqueResultOptional();
            if (orderOptional.isPresent() && productOptional.isPresent() && productOptional.get().getQuantity() >= request.getQuantity()) {
                Product product = productOptional.get();
                int currentQuantity = product.getQuantity();
                product.setQuantity(currentQuantity - request.getQuantity());
                session.merge(product);
                if (orderItemOptional.isEmpty()) {
                    Order order = orderOptional.get();
                    OrderItem orderItem = OrderItem.builder()
                            .quantity(request.getQuantity())
                            .unitPrice(product.getSalePrice())
                            .order(order)
                            .orderId(order.getId())
                            .product(product)
                            .productId(product.getId())
                            .build();
                    session.persist(orderItem);
                    orderItemId = orderItem.getId();

                } else {
                    OrderItem orderItem = orderItemOptional.get();
                    orderItem.setQuantity(orderItem.getQuantity() + request.getQuantity());
                    orderItemId = orderItem.getId();
                    session.merge(orderItem);
                }
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orderItemId;
    }

    @Override
    public boolean update(OrderItemUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Optional<Product> productOptional = session.createQuery("select p from Product p where p.id =: id and p.deleted is false", Product.class)
                    .setParameter("id", request.getProductId())
                    .uniqueResultOptional();
            OrderItem orderItem = session.find(OrderItem.class, request.getId());
            Product product = productOptional.get();
            int quantityInStock = product.getQuantity() + orderItem.getQuantity();
            if (productOptional.isPresent() && quantityInStock >= request.getQuantity()) {
                orderItem.setQuantity(request.getQuantity());
                product.setQuantity(quantityInStock - request.getQuantity());
                session.merge(product);
                session.merge(orderItem);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean delete(Long idEntity) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        OrderItem orderItem = session.find(OrderItem.class, idEntity);
        try {
            Optional<Product> productOptional = session.createQuery("select p from Product p join OrderItem oi on p.id = oi.productId where oi.id =: id and oi.deleted is false", Product.class)
                    .setParameter("id", idEntity)
                    .uniqueResultOptional();
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setQuantity(product.getQuantity() + orderItem.getQuantity());
                session.merge(product);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return HibernateUtils.remove(orderItem);
    }

    @Override
    public OrderItemViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        OrderItem orderItem = session.find(OrderItem.class, entityId);
        OrderItemViewModel orderItemViewModel = OrderItemMapper.getInstance.entityToViewModel(orderItem);
        return orderItemViewModel;
    }

    @Override
    public List<OrderItemViewModel> retrieveAll(OrderItemPagingRequest request) {
        List<OrderItemViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("OrderItem", request);
        Query query = session.createQuery(cmd, Feature.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<OrderItem> orderItemList = query.getResultList();

        for (OrderItem orderItem : orderItemList) {
            list.add(OrderItemMapper.getInstance.entityToViewModel(orderItem));
        }
        session.close();
        return list;
    }

    @Override
    public List<CardItemViewModel> retrieveAllCartItem(Long orderId, CardItemPagingRequest request) {
        List<CardItemViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("OrderItem", request);
        Query query = session.createQuery(cmd, OrderItem.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<OrderItem> orderItems = query.getResultList();

        for (OrderItem orderItem : orderItems) {
            list.add(OrderItemMapper.getInstance.entityToCardItemViewModel(orderItem));
        }
        session.close();
        return list;
    }

    @Override
    public boolean deleteAllCardItem(Long orderId) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Order order = session.find(Order.class, orderId);
            List<OrderItem> orderItems = session.createQuery("select oi from OrderItem oi where oi.orderId =: orderId", OrderItem.class)
                    .setParameter("orderId", order.getId())
                    .getResultList();
            if (!orderItems.isEmpty()) {
                for (OrderItem orderItem : orderItems) {
                    Product product = session.find(Product.class, orderItem.getProductId());
                    product.setQuantity(product.getQuantity() + orderItem.getQuantity());
                    session.merge(product);
                    session.remove(orderItem);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
}
