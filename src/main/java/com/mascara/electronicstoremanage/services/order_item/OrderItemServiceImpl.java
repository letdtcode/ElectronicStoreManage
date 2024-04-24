package com.mascara.electronicstoremanage.services.order_item;

import com.mascara.electronicstoremanage.repositories.order_item.OrderItemRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemViewModel;
import com.mascara.electronicstoremanage.view_model.sale.CartItemCreateRequest;
import com.mascara.electronicstoremanage.view_model.sale.CartItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.sale.CartItemUpdateRequest;
import com.mascara.electronicstoremanage.view_model.sale.CartItemViewModel;

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
    public Long insertOrderItem(CartItemCreateRequest request) {
        return OrderItemRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateOrderItem(CartItemUpdateRequest request) {
        return OrderItemRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteOrderItem(Long id) {
        return OrderItemRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public boolean deleteAllCardItem(Long orderId) {
        return OrderItemRepositoryImpl.getInstance().deleteAllCardItem(orderId);
    }

    @Override
    public OrderItemViewModel retrieveOrderItemById(Long id) {
        return OrderItemRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<OrderItemViewModel> retrieveAllOrderItem(OrderItemPagingRequest request) {
        return OrderItemRepositoryImpl.getInstance().retrieveAll(request);
    }

    @Override
    public List<CartItemViewModel> retrieveAllCartItem(Long orderId, CartItemPagingRequest request) {
        return OrderItemRepositoryImpl.getInstance().retrieveAllCartItem(orderId, request);
    }
}
