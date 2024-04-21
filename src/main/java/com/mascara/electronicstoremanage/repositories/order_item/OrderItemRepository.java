package com.mascara.electronicstoremanage.repositories.order_item;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemCreateRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemUpdateRequest;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemViewModel;
import com.mascara.electronicstoremanage.view_model.sale.CardItemPagingRequest;
import com.mascara.electronicstoremanage.view_model.sale.CardItemViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : OrderItemRepository
 */
public interface OrderItemRepository extends ModifyEntityRequest<OrderItemCreateRequest, OrderItemUpdateRequest, Long>,
        RetrieveEntityRequest<OrderItemViewModel, OrderItemPagingRequest, Long> {
    List<CardItemViewModel> retrieveAllCartItem(Long orderId, CardItemPagingRequest request);
}
