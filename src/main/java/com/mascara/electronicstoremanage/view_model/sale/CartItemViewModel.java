package com.mascara.electronicstoremanage.view_model.sale;

import com.mascara.electronicstoremanage.utils.CurrencyUtils;
import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 20/04/2024
 * Time      : 6:50 CH
 * Filename  : CardItemViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemViewModel {
    private Long id;
    private Long productId;
    private String productName;
    private Double unitPrice;
    private Integer quantity;
    private String unitPriceShow;
}
