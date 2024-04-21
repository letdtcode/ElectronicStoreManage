package com.mascara.electronicstoremanage.repositories.order;

import com.mascara.electronicstoremanage.common.mapper.OrderMapper;
import com.mascara.electronicstoremanage.entities.Brand;
import com.mascara.electronicstoremanage.entities.Order;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.customer.HistoryOrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.customer.HistoryOrderViewModel;
import com.mascara.electronicstoremanage.view_model.order.OrderCreateRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderUpdateRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;
import com.mascara.electronicstoremanage.view_model.sale.OrderWaitingViewModel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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
        if (instance == null)
            instance = new OrderRepositoryImpl();
        return instance;
    }

    private OrderRepositoryImpl() {

    }

    @Override
    public Long insert(OrderCreateRequest request) {
        return null;
    }

    @Override
    public boolean update(OrderUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public OrderViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<OrderViewModel> retrieveAll(OrderPagingRequest request) {
        return null;
    }

    @Override
    public List<OrderWaitingViewModel> retrieveOrderListWaiting(OrderPagingRequest request) {
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
}
