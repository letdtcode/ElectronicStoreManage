package com.mascara.electronicstoremanage.services.order_item;

import com.mascara.electronicstoremanage.repositories.order_item.OrderItemRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemCreateRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemUpdateRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:35 CH
 * Filename  : OrderItemServiceImpl
 */
public class OrderItemServiceImpl implements OrderItemService {
    private static OrderItemServiceImpl instance = null;

    public static OrderItemServiceImpl getInstance() {
        if (instance == null)
            instance = new OrderItemServiceImpl();
        return instance;
    }

    private OrderItemServiceImpl() {

    }

    @Override
    public Long insertOrderItem(OrderItemCreateRequest request) {
        return OrderItemRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateOrderItem(OrderItemUpdateRequest request) {
        return OrderItemRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteOrderItem(Long id) {
        return OrderItemRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public OrderItemViewModel retrieveOrderItemById(Long id) {
        return OrderItemRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<OrderItemViewModel> retrieveAllOrderItem(OrderItemPagingRequest request) {
        return OrderItemRepositoryImpl.getInstance().retrieveAll(request);
    }
}
