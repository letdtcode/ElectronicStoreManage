package com.mascara.electronicstoremanage.repositories.order;

import com.mascara.electronicstoremanage.common.mapper.OrderMapper;
import com.mascara.electronicstoremanage.entities.*;
import com.mascara.electronicstoremanage.enums.order.ModeOfPaymentEnum;
import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import com.mascara.electronicstoremanage.utils.CurrencyUtils;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.customer.HistoryOrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.customer.HistoryOrderViewModel;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;
import com.mascara.electronicstoremanage.view_model.sale.OrderCreateRequest;
import com.mascara.electronicstoremanage.view_model.sale.OrderUpdateRequest;
import com.mascara.electronicstoremanage.view_model.sale.OrderWaitingPagingRequest;
import com.mascara.electronicstoremanage.view_model.sale.OrderWaitingViewModel;
import com.mascara.electronicstoremanage.view_model.statistic.OrderCancelStatisticPagingRequest;
import com.mascara.electronicstoremanage.view_model.statistic.OrderCancelStatisticViewModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:23 CH
 * Filename  : OrderRepositoryImpl
 */
public class OrderRepositoryImpl implements OrderRepository {
    private static OrderRepositoryImpl instance = null;

    public static OrderRepositoryImpl getInstance() {
        if (instance == null) instance = new OrderRepositoryImpl();
        return instance;
    }

    private OrderRepositoryImpl() {

    }

    @Override
    public Long insert(OrderCreateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;

        Long orderId = -1L;
        try {
            tx = session.beginTransaction();
            Staff staff = session.find(Staff.class, request.getStaffId());
            Customer customer = session.find(Customer.class, request.getCustomerId());
            Order order = Order.builder().staff(staff).staffId(staff.getId()).customer(customer).customerId(customer.getId()).status(request.getStatus()).build();
            session.persist(order);
            orderId = order.getId();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orderId;
    }

    @Override
    public boolean update(OrderUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        try {
            Order order = session.find(Order.class, request.getId());
            Staff staff = session.find(Staff.class, request.getStaffId());
            Customer customer = session.find(Customer.class, request.getCustomerId());
            order.setTotalBill(request.getTotalBill());
            order.setTotalPay(request.getTotalPay());
            order.setChangeMoney(request.getChangeMoney());
            order.setModeOfDelivery(request.getModeOfDelivery());
            order.setModeOfPayment(request.getModeOfPayment());
            order.setNote(request.getNote());
            order.setStatus(request.getStatus());
            order.setStaff(staff);
            order.setStaffId(staff.getId());
            order.setCustomer(customer);
            order.setCustomerId(customer.getId());
            return HibernateUtils.merge(order);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        Session session = HibernateUtils.getSession();
        Order order = session.find(Order.class, idEntity);
        order.setDeleted(true);
        session.close();
        return HibernateUtils.merge(order);
    }

    @Override
    public OrderViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Order order = session.find(Order.class, entityId);
        OrderViewModel orderViewModel = OrderMapper.getInstance.entityToViewModel(order);
        return orderViewModel;
    }

    @Override
    public List<OrderViewModel> retrieveAll(OrderPagingRequest request) {
        List<OrderViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Order", request);
        Query query = session.createQuery(cmd, Order.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Order> orderList = query.getResultList();

        for (Order order : orderList) {
            list.add(OrderMapper.getInstance.entityToViewModel(order));
        }
        session.close();
        return list;
    }

    @Override
    public boolean cancelOrder(Long orderId) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Order order = session.find(Order.class, orderId);
            order.setStatus(OrderStatusEnum.CANCELED);

            Set<OrderItem> orderItemSet = order.getOrderItemSet();
            for (OrderItem item : orderItemSet) {
                long productId = item.getProductId();
                Product product = session.find(Product.class, productId);
                int currentQuantity = product.getQuantity();
                product.setQuantity(currentQuantity + item.getQuantity());
                session.merge(product);
            }
            transaction.commit();
            return HibernateUtils.merge(order);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<OrderWaitingViewModel> retrieveOrderListWaiting(OrderWaitingPagingRequest request) {
        List<OrderWaitingViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Order", request);
        Query query = session.createQuery(cmd, Brand.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Order> orders = query.getResultList();

        for (Order order : orders) {
            list.add(OrderMapper.getInstance.entityToWaitingViewModel(order));
        }
        session.close();
        return list;
    }

    @Override
    public List<HistoryOrderViewModel> retrieveHistoryOrderCustomer(HistoryOrderPagingRequest request) {
        List<HistoryOrderViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Order", request);
        Query query = session.createQuery(cmd, Order.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Order> orders = query.getResultList();

        for (Order order : orders) {
            list.add(OrderMapper.getInstance.entityToHistoryOrderViewModel(order));
        }
        session.close();
        return list;
    }

    @Override
    public Long countNumberOfOrderRangeDate(LocalDate dateStart, LocalDate dateEnd, OrderStatusEnum status) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        long result = 0;
        try {
            tx = session.beginTransaction();
            if ((dateEnd == null && dateStart != null) || (dateStart == dateEnd && dateStart != null)) {
                result = session.createQuery("select count(o.id) from Order o where o.lastModifiedDate =: dateStart and o.status =: status and o.deleted is false", Long.class).setParameter("dateStart", dateStart.atStartOfDay()).setParameter("status", status).uniqueResult();
            } else if (dateStart != null && dateEnd != null) {
                result = session.createQuery("select count(o.id) from Order o where o.lastModifiedDate >=: dateStart and o.lastModifiedDate <=: dateEnd and o.status =: status and o.deleted is false", Long.class).setParameter("dateStart", dateStart.atStartOfDay()).setParameter("dateEnd", dateEnd.atTime(LocalTime.MAX)).setParameter("status", status).uniqueResult();
            } else if (dateStart == null && dateEnd == null) {
                result = session.createQuery("select count(o.id) from Order o where o.status =: status and o.deleted is false", Long.class).setParameter("status", status).uniqueResult();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<OrderCancelStatisticViewModel> retrieveOrderCancelStatistic(OrderCancelStatisticPagingRequest request) {
        List<OrderCancelStatisticViewModel> result = null;
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int offset = (request.getPageIndex() - 1) * request.getPageSize();
            LocalDate dateStart = request.getDateStart();
            LocalDate dateEnd = request.getDateEnd();
            if ((dateEnd == null && dateStart != null) || (dateStart == dateEnd && dateStart != null)) {
                result = session.createQuery("select o.id,sum(oi.unitPrice*oi.quantity) as totalPay,o.modeOfPayment,o.lastModifiedDate,c.fullName,o.note from Order o left join OrderItem oi on o.id = oi.orderId join Customer c on o.customerId = c.id where o.status =: orderStatus and DATE(o.lastModifiedDate) =: dateStart GROUP BY o.id", OrderCancelStatisticViewModel.class).setParameter("orderStatus", OrderStatusEnum.CANCELED).setParameter("dateStart", dateStart).setFirstResult(offset).setMaxResults(request.getPageSize()).getResultList();
            } else if (dateStart != null && dateEnd != null) {
                result = session.createQuery("select o.id,sum(oi.unitPrice*oi.quantity) as totalPay,o.modeOfPayment,o.lastModifiedDate,c.fullName,o.note from Order o left join OrderItem oi on o.id = oi.orderId join Customer c on o.customerId = c.id where o.status =: orderStatus and DATE(o.lastModifiedDate) >=: dateStart and DATE(o.lastModifiedDate) <=: dateEnd GROUP BY o.id", OrderCancelStatisticViewModel.class).setParameter("orderStatus", OrderStatusEnum.CANCELED).setParameter("dateStart", dateStart).setParameter("dateEnd", dateEnd).setFirstResult(offset).setMaxResults(request.getPageSize()).getResultList();
            } else if (dateStart == null && dateEnd == null) {
                result = session.createQuery("select o.id,sum(oi.unitPrice*oi.quantity) as totalPay,o.modeOfPayment,o.lastModifiedDate,c.fullName,o.note from Order o left join OrderItem oi on o.id = oi.orderId join Customer c on o.customerId = c.id where o.status =: orderStatus GROUP BY o.id", OrderCancelStatisticViewModel.class).setParameter("orderStatus", OrderStatusEnum.CANCELED).setFirstResult(offset).setMaxResults(request.getPageSize()).getResultList();
            }
            tx.commit();
            for (OrderCancelStatisticViewModel viewModel : result) {
                viewModel.setModeOfPaymentShow(viewModel.getModeOfPayment() != null ? viewModel.getModeOfPayment().getDisplay() : ModeOfPaymentEnum.CASH.getDisplay());
                viewModel.setTotalPayShow(viewModel.getTotalPay() != null ? CurrencyUtils.getInstance().convertVietnamCurrency(viewModel.getTotalPay()) : CurrencyUtils.getInstance().convertVietnamCurrency(0));
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}
