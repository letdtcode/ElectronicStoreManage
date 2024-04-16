package com.mascara.electronicstoremanage.view_model.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:12 CH
 * Filename  : CategoryUpdateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest {
    private Long id;
    private String categoryName;
}
