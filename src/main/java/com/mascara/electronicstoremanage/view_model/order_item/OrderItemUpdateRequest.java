package com.mascara.electronicstoremanage.view_model.order_item;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:20 CH
 * Filename  : OrderItemUpdateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemUpdateRequest {
    private Long id;
    private Long productId;
    private Integer quantity;
}
