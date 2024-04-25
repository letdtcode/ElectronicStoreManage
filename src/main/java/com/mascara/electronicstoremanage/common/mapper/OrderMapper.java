package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Customer;
import com.mascara.electronicstoremanage.entities.Order;
import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.enums.order.ModeOfDeliveryEnum;
import com.mascara.electronicstoremanage.enums.order.ModeOfPaymentEnum;
import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import com.mascara.electronicstoremanage.utils.CurrencyUtils;
import com.mascara.electronicstoremanage.view_model.customer.HistoryOrderViewModel;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;
import com.mascara.electronicstoremanage.view_model.sale.OrderWaitingViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Mapping(source = "totalBill", target = "totalBillShow", qualifiedByName = "mapToCurrencyVietnam")
    @Mapping(source = "totalPay", target = "totalPayShow", qualifiedByName = "mapToCurrencyVietnam")
    @Mapping(source = "changeMoney", target = "changeMoneyShow", qualifiedByName = "mapToCurrencyVietnam")
    @Mapping(source = "modeOfDelivery", target = "modeOfDeliveryShow", qualifiedByName = "mapToModeDeliveryShow")
    @Mapping(source = "modeOfPayment", target = "modeOfPaymentShow", qualifiedByName = "mapToModePaymentShow")
    @Mapping(source = "status", target = "statusShow", qualifiedByName = "mapToStatusShow")
    @Mapping(source = "staff", target = "fullNameStaff", qualifiedByName = "mapStaffToStaffName")
    @Mapping(source = "customer", target = "fullNameCustomer", qualifiedByName = "mapCustomerToCustomerName")
    @Mapping(source = "createdDate", target = "dateCheckout", qualifiedByName = "mapLocalDateTimeToLocalDate")
    OrderViewModel entityToViewModel(Order order);

    @Mapping(source = "staff", target = "nameStaff", qualifiedByName = "mapStaffToStaffName")
    @Mapping(source = "customer", target = "nameCustomer", qualifiedByName = "mapCustomerToCustomerName")
    OrderWaitingViewModel entityToWaitingViewModel(Order order);

    HistoryOrderViewModel entityToHistoryOrderViewModel(Order order);

    @Named("mapToCurrencyVietnam")
    default String mapToCurrencyVietnam(Double money) {
        return money != null ? CurrencyUtils.getInstance().convertVietnamCurrency(money) : 0 + " " + CurrencyUtils.getInstance().getSymbolCurrencyVietnam();
    }

    @Named("mapToModeDeliveryShow")
    default String mapToModeDeliveryShow(ModeOfDeliveryEnum modeOfDeliveryEnum) {
        return modeOfDeliveryEnum != null ? modeOfDeliveryEnum.getDisplay() : null;
    }

    @Named("mapToModePaymentShow")
    default String mapToModePaymentShow(ModeOfPaymentEnum modeOfPaymentEnum) {
        return modeOfPaymentEnum != null ? modeOfPaymentEnum.getDisplay() : null;
    }

    @Named("mapToStatusShow")
    default String mapToStatusShow(OrderStatusEnum statusEnum) {
        return statusEnum.getDisplay();
    }

    @Named("mapLocalDateTimeToLocalDate")
    default LocalDate mapToStatusShow(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    @Named("mapStaffToStaffName")
    default String mapStaffToStaffName(Staff staff) {
        return staff.getFullName();
    }

    @Named("mapCustomerToCustomerName")
    default String mapCustomerToCustomerName(Customer customer) {
        return customer.getFullName();
    }
}
