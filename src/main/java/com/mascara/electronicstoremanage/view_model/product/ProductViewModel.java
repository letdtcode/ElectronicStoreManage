package com.mascara.electronicstoremanage.view_model.product;

import com.mascara.electronicstoremanage.enums.product.ProductStatusEnum;
import com.mascara.electronicstoremanage.enums.product.WarrantyPeriodUnitENum;
import com.mascara.electronicstoremanage.enums.product.WeightUnitEnum;
import lombok.*;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:21 CH
 * Filename  : ProductViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductViewModel {
    private Long id;
    private String productName;
    private String description;
    private String pathImage;
    private Double salePrice;
    private Double importPrice;
    private Integer quantity;
    private String origin;
    private Double weight;
    private WeightUnitEnum weightUnit;
    private Integer warrantyPeriod;
    private WarrantyPeriodUnitENum warrantyPeriodUnit;
    private String size;
    private String brandName;
    private String materialName;
    private String categoryName;
    private List<String> featureNameList;
    private String featureNameListShow;
    private List<String> colorNameList;
    private String colorNameListShow;
    private ProductStatusEnum status;
}
