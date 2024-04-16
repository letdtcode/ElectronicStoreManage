package com.mascara.electronicstoremanage.view_model.category;

import jakarta.persistence.Column;
import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:12 CH
 * Filename  : CategoryCreateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCreateRequest {
    private String categoryName;
}
