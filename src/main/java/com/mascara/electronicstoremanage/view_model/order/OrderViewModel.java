package com.mascara.electronicstoremanage.view_model.order;

import com.mascara.electronicstoremanage.enums.order.ModeOfDeliveryEnum;
import com.mascara.electronicstoremanage.enums.order.ModeOfPaymentEnum;
import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import lombok.*;

import java.time.LocalDate;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:20 CH
 * Filename  : OrderViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderViewModel {
    private Long id;
    private Double totalBill;
    private String totalBillShow;
    private Double totalPay;
    private String totalPayShow;
    private Double changeMoney;
    private String changeMoneyShow;
    private ModeOfDeliveryEnum modeOfDelivery;
    private String modeOfDeliveryShow;
    private ModeOfPaymentEnum modeOfPayment;
    private String modeOfPaymentShow;
    private OrderStatusEnum status;
    private String statusShow;
    private Long staffId;
    private String fullNameStaff;
    private Long customerId;
    private String fullNameCustomer;
    private String note;
    private LocalDate dateCheckout;
}
