package com.mascara.electronicstoremanage.view_model.statistic;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 01/05/2024
 * Time      : 5:43 CH
 * Filename  : ProductStatisticViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductStatisticViewModel {
    private Long id;
    private String productName;
    private String code;
    private Double revenueMoney;
    private Long quantity;
    private String revenueMoneyShow;
    private String categoryName;

    public ProductStatisticViewModel(Long id, String productName, String code, Double revenueMoney, Long quantity) {
        this.id = id;
        this.productName = productName;
        this.code = code;
        this.revenueMoney = revenueMoney;
        this.quantity = quantity;
    }
}
