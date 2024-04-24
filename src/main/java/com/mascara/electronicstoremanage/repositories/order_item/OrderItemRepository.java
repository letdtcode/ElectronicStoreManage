package com.mascara.electronicstoremanage.repositories.order_item;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
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
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : OrderItemRepository
 */
public interface OrderItemRepository extends ModifyEntityRequest<CartItemCreateRequest, CartItemUpdateRequest, Long>,
        RetrieveEntityRequest<OrderItemViewModel, OrderItemPagingRequest, Long> {
    List<CartItemViewModel> retrieveAllCartItem(Long orderId, CartItemPagingRequest request);

    boolean deleteAllCardItem(Long orderId);
}
