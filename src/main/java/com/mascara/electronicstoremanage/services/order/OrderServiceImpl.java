package com.mascara.electronicstoremanage.services.order;

import com.mascara.electronicstoremanage.repositories.order.OrderRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.order.OrderCreateRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderUpdateRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:35 CH
 * Filename  : OrderServiceImpl
 */
public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl instance = null;

    public static OrderServiceImpl getInstance() {
        if (instance == null)
            instance = new OrderServiceImpl();
        return instance;
    }

    private OrderServiceImpl() {

    }

    @Override
    public Long insertOrder(OrderCreateRequest request) {
        return OrderRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateOrder(OrderUpdateRequest request) {
        return OrderRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteOrder(Long id) {
        return OrderRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public OrderViewModel retrieveOrderById(Long id) {
        return OrderRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<OrderViewModel> retrieveAllOrder(OrderPagingRequest request) {
        return OrderRepositoryImpl.getInstance().retrieveAll(request);
    }
}