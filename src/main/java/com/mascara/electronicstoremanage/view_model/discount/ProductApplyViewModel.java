package com.mascara.electronicstoremanage.view_model.discount;

import javafx.scene.control.CheckBox;
import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 23/04/2024
 * Time      : 7:34 CH
 * Filename  : ProductApplyDiscountViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductApplyViewModel {
    private CheckBox checkBox;
    private Long id;
    private String productName;
    private String code;
    private Double salePrice;
    private String salePriceShow;
}
