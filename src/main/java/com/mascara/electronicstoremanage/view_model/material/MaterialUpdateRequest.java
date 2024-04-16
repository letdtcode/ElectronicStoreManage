package com.mascara.electronicstoremanage.view_model.material;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:19 CH
 * Filename  : MaterialUpdateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialUpdateRequest {
    private Long id;
    private String materialName;
}
