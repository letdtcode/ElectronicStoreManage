package com.mascara.electronicstoremanage.view_model.sale;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 23/04/2024
 * Time      : 5:53 CH
 * Filename  : CartItemUpdateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemUpdateRequest {
    private Long id;
    private Long productId;
    private Integer quantity;
}