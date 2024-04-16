package com.mascara.electronicstoremanage.services.order_item;

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
 * Filename  : OrderItemService
 */
public interface OrderItemService {
    Long insertOrderItem(OrderItemCreateRequest request);

    boolean updateOrderItem(OrderItemUpdateRequest request);

    boolean deleteOrderItem(Long id);

    OrderItemViewModel retrieveOrderItemById(Long id);

    List<OrderItemViewModel> retrieveAllOrderItem(OrderItemPagingRequest request);
}
