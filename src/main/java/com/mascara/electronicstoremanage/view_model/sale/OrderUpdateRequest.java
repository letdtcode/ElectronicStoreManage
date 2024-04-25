package com.mascara.electronicstoremanage.view_model.sale;

import com.mascara.electronicstoremanage.enums.order.ModeOfDeliveryEnum;
import com.mascara.electronicstoremanage.enums.order.ModeOfPaymentEnum;
import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:20 CH
 * Filename  : OrderUpdateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderUpdateRequest {
    private Long Id;
    private Double totalBill;
    private Double totalPay;
    private Double changeMoney;
    private ModeOfDeliveryEnum modeOfDelivery;
    private ModeOfPaymentEnum modeOfPayment;
    private String note;
    private OrderStatusEnum status;
    private Long staffId;
    private Long customerId;
}
