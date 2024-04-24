package com.mascara.electronicstoremanage.view_model.sale;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 23/04/2024
 * Time      : 5:51 CH
 * Filename  : CartItemCreateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemCreateRequest {
    private Integer quantity;
    private Long orderId;
    private Long productId;
}
