package com.mascara.electronicstoremanage.repositories.order_item;

import com.mascara.electronicstoremanage.repositories.material.MaterialRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemCreateRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemUpdateRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : OrderItemRepositoryImpl
 */
public class OrderItemRepositoryImpl implements OrderItemRepository{
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
}
