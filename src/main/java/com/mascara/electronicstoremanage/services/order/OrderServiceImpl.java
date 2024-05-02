package com.mascara.electronicstoremanage.services.order;

import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import com.mascara.electronicstoremanage.repositories.order.OrderRepositoryImpl;
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
 * Date      : 16/04/2024
 * Time      : 6:35 CH
 * Filename  : OrderServiceImpl
 */
public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl instance = null;

    public static OrderServiceImpl getInstance() {
        if (instance == null)
            instance = new OrderServiceImpl();
        return instance;
    }

    private OrderServiceImpl() {

    }

    @Override
    public Long insertOrder(OrderCreateRequest request) {
        return OrderRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateOrder(OrderUpdateRequest request) {
        return OrderRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteOrder(Long id) {
        return OrderRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public boolean cancelOrder(Long id) {
        return OrderRepositoryImpl.getInstance().cancelOrder(id);
    }

    @Override
    public OrderViewModel retrieveOrderById(Long id) {
        return OrderRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<OrderViewModel> retrieveAllOrder(OrderPagingRequest request) {
        return OrderRepositoryImpl.getInstance().retrieveAll(request);
    }

    @Override
    public List<OrderWaitingViewModel> retrieveOrderListWaiting(OrderWaitingPagingRequest request) {
        return OrderRepositoryImpl.getInstance().retrieveOrderListWaiting(request);
    }

    @Override
    public List<HistoryOrderViewModel> retrieveHistoryOrderCustomer(HistoryOrderPagingRequest request) {
        return OrderRepositoryImpl.getInstance().retrieveHistoryOrderCustomer(request);
    }

    @Override
    public Long countNumberOfOrderRangeDate(LocalDate dateStart, LocalDate dateEnd, OrderStatusEnum status) {
        return OrderRepositoryImpl.getInstance().countNumberOfOrderRangeDate(dateStart, dateEnd, status);
    }

    @Override
    public List<OrderCancelStatisticViewModel> retrieveOrderCancelStatistic(OrderCancelStatisticPagingRequest request) {
        return OrderRepositoryImpl.getInstance().retrieveOrderCancelStatistic(request);
    }
}
