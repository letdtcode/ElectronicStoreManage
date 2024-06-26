package com.mascara.electronicstoremanage.repositories.order;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import com.mascara.electronicstoremanage.view_model.customer.HistoryOrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.customer.HistoryOrderViewModel;
import com.mascara.electronicstoremanage.view_model.order.OrderPagingRequest;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;
import com.mascara.electronicstoremanage.view_model.sale.OrderCreateRequest;
import com.mascara.electronicstoremanage.view_model.sale.OrderUpdateRequest;
import com.mascara.electronicstoremanage.view_model.sale.OrderWaitingPagingRequest;
import com.mascara.electronicstoremanage.view_model.sale.OrderWaitingViewModel;
import com.mascara.electronicstoremanage.view_model.statistic.OrderCancelStatisticPagingRequest;
import com.mascara.electronicstoremanage.view_model.statistic.OrderCancelStatisticViewModel;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:23 CH
 * Filename  : OrderRepository
 */
public interface OrderRepository extends ModifyEntityRequest<OrderCreateRequest, OrderUpdateRequest, Long>,
        RetrieveEntityRequest<OrderViewModel, OrderPagingRequest, Long> {

    boolean cancelOrder(Long orderId);

    List<OrderWaitingViewModel> retrieveOrderListWaiting(OrderWaitingPagingRequest request);

    List<HistoryOrderViewModel> retrieveHistoryOrderCustomer(HistoryOrderPagingRequest request);

    Long countNumberOfOrderRangeDate(LocalDate dateStart, LocalDate dateEnd, OrderStatusEnum status);

    List<OrderCancelStatisticViewModel> retrieveOrderCancelStatistic(OrderCancelStatisticPagingRequest request);
}
