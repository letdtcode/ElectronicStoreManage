package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Customer;
import com.mascara.electronicstoremanage.entities.Order;
import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.view_model.customer.HistoryOrderViewModel;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;
import com.mascara.electronicstoremanage.view_model.sale.OrderWaitingViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:49 CH
 * Filename  : OrderMapper
 */
@Mapper
public interface OrderMapper {
    OrderMapper getInstance = Mappers.getMapper(OrderMapper.class);

    OrderViewModel entityToViewModel(Order order);

    @Mapping(source = "staff", target = "nameStaff", qualifiedByName = "mapStaffToStaffName")
    @Mapping(source = "customer", target = "nameCustomer", qualifiedByName = "mapCustomerToCustomerName")
    OrderWaitingViewModel entityToWaitingViewModel(Order order);

    HistoryOrderViewModel entityToHistoryOrderViewModel(Order order);

    @Named("mapStaffToStaffName")
    default String mapStaffToStaffName(Staff staff) {
        return staff.getFullName();
    }

    @Named("mapCustomerToCustomerName")
    default String mapCustomerToCustomerName(Customer customer) {
        return customer.getFullName();
    }
}
