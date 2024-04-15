package com.mascara.electronicstoremanage.view_model.brand;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:04 CH
 * Filename  : BrandUpdateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandUpdateRequest {
    private Long id;
    private String brandName;
}
