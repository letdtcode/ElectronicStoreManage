package com.mascara.electronicstoremanage.view_model.order;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:20 CH
 * Filename  : OrderItemViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemViewModel {
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private String unitPriceShow;
    private Long orderId;
    private Long productId;
    private String productName;
    private Double totalItem;
    private String totalItemShow;
}
