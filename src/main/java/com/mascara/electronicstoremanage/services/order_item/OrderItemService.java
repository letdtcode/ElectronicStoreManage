package com.mascara.electronicstoremanage.services.order_item;

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
 * Filename  : OrderItemService
 */
public interface OrderItemService {
    Long insertOrderItem(CartItemCreateRequest request);

    boolean updateOrderItem(CartItemUpdateRequest request);

    boolean deleteOrderItem(Long id);

    boolean deleteAllCardItem(Long orderId);

    OrderItemViewModel retrieveOrderItemById(Long id);

    List<OrderItemViewModel> retrieveAllOrderItem(OrderItemPagingRequest request);

    List<CartItemViewModel> retrieveAllCartItem(Long orderId, CartItemPagingRequest request);
}
