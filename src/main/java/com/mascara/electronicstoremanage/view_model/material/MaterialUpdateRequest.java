package com.mascara.electronicstoremanage.view_model.material;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class MaterialUpdateRequest {
    private Long id;
    private String materialName;
}
