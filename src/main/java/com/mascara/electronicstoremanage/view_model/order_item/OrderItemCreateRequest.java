package com.mascara.electronicstoremanage.view_model.order_item;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:20 CH
 * Filename  : OrderItemCreateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemCreateRequest {
    private Integer quantity;
    private Long orderId;
    private Long productId;
}
