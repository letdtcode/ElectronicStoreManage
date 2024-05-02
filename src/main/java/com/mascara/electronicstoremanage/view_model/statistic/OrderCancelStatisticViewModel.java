package com.mascara.electronicstoremanage.view_model.statistic;

import com.mascara.electronicstoremanage.enums.order.ModeOfPaymentEnum;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 01/05/2024
 * Time      : 5:43 CH
 * Filename  : OrderCancelStatisticViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCancelStatisticViewModel {
    private Long idOrder;
    private Double totalPay;
    private String totalPayShow;
    private ModeOfPaymentEnum modeOfPayment;
    private String modeOfPaymentShow;
    private LocalDateTime timeCheckout;
    private String nameCustomer;
    private String note;

    public OrderCancelStatisticViewModel(Long idOrder, Double totalPay, ModeOfPaymentEnum modeOfPaymentEnum,
                                         LocalDateTime timeCheckout, String nameCustomer, String note) {
        this.idOrder = idOrder;
        this.totalPay = totalPay;
        this.modeOfPayment = modeOfPaymentEnum;
        this.timeCheckout = timeCheckout;
        this.nameCustomer = nameCustomer;
        this.note = note;
    }
}
