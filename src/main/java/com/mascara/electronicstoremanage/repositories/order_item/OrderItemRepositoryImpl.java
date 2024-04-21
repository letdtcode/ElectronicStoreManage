package com.mascara.electronicstoremanage.repositories.order_item;

import com.mascara.electronicstoremanage.common.mapper.OrderItemMapper;
import com.mascara.electronicstoremanage.entities.OrderItem;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemCreateRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemUpdateRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemViewModel;
import com.mascara.electronicstoremanage.view_model.sale.CardItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.sale.CardItemViewModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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
        return null;
    }

    @Override
    public boolean update(OrderItemUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public OrderItemViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<OrderItemViewModel> retrieveAll(OrderItemPagingRequest request) {
        return null;
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
}
