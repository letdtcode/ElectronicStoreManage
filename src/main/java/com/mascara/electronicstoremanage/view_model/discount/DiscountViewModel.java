package com.mascara.electronicstoremanage.view_model.discount;

import com.mascara.electronicstoremanage.enums.discount.DiscountStatus;
import com.mascara.electronicstoremanage.enums.discount.TypeDiscountEnum;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:17 CH
 * Filename  : DiscountViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountViewModel {
    private Long id;
    private String capaignName;
    private TypeDiscountEnum typeDiscount;
    private Double discountValue;
    private String discountValueShow;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String description;
    private List<Long> idProductList;
    private DiscountStatus status;
}
