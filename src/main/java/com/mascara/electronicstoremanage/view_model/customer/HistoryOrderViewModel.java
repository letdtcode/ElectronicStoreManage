package com.mascara.electronicstoremanage.view_model.customer;

import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import lombok.*;

import java.time.LocalDate;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 20/04/2024
 * Time      : 9:28 CH
 * Filename  : HistoryOrderViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryOrderViewModel {
    private Long id;
    private Double totalBill;
    private Double totalPay;
    private Double changeMoney;
    private LocalDate createdDate;
    private OrderStatusEnum status;
}
