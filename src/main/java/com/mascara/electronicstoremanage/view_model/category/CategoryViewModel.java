package com.mascara.electronicstoremanage.view_model.category;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:13 CH
 * Filename  : CategoryViewModel
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryViewModel {
    private Long id;
    private String categoryName;
}
