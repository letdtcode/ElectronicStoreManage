package com.mascara.electronicstoremanage.view_model.sale;

import com.mascara.electronicstoremanage.enums.product.WarrantyPeriodUnitENum;
import com.mascara.electronicstoremanage.enums.product.WeightUnitEnum;
import lombok.*;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 18/04/2024
 * Time      : 11:47 CH
 * Filename  : ProductSaleViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSaleViewModel {
    private Long id;
    private String productName;
    private Double salePrice;
    private Double quantity;
    private String origin;
    private Double weight;
    private WeightUnitEnum weightUnit;
    private Integer warrantyPeriod;
    private WarrantyPeriodUnitENum warrantyPeriodUnit;
    private String size;
    private String brandName;
    private String materialName;
    private String categoryName;
    private List<String> colorNameList;
    private String colorNameListShow;
    private Double discountValue;
}
