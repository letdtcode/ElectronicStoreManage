package com.mascara.electronicstoremanage.view_model.sale;

import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:19 CH
 * Filename  : OrderCreateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateRequest {
    private OrderStatusEnum status;
    private Long staffId;
    private Long customerId;
}
