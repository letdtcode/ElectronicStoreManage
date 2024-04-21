package com.mascara.electronicstoremanage.services.order;

import com.mascara.electronicstoremanage.view_model.customer.HistoryOrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.customer.HistoryOrderViewModel;
import com.mascara.electronicstoremanage.view_model.order.OrderCreateRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderUpdateRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;
import com.mascara.electronicstoremanage.view_model.sale.OrderWaitingViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:35 CH
 * Filename  : OrderService
 */
public interface OrderService {
    Long insertOrder(OrderCreateRequest request);

    boolean updateOrder(OrderUpdateRequest request);

    boolean deleteOrder(Long id);

    OrderViewModel retrieveOrderById(Long id);

    List<OrderViewModel> retrieveAllOrder(OrderPagingRequest request);
    List<OrderWaitingViewModel> retrieveOrderListWaiting(OrderPagingRequest request);
    List<HistoryOrderViewModel> retrieveHistoryOrderCustomer(HistoryOrderPagingRequest request);

}
