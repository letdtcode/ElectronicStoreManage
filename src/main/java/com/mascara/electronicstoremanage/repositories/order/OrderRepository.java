package com.mascara.electronicstoremanage.repositories.order;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderCreateRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderUpdateRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:23 CH
 * Filename  : OrderRepository
 */
public interface OrderRepository extends ModifyEntityRequest<OrderCreateRequest, OrderUpdateRequest, Long>,
        RetrieveEntityRequest<OrderViewModel, OrderPagingRequest, Long> {
}
