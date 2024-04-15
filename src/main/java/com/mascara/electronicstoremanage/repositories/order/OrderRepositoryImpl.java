package com.mascara.electronicstoremanage.repositories.order;

import com.mascara.electronicstoremanage.repositories.material.MaterialRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.order.OrderCreateRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderUpdateRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;

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
}
