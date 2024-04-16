package com.mascara.electronicstoremanage.view_model.color;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:13 CH
 * Filename  : ColorUpdateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ColorUpdateRequest {
    private Long id;
    private String colorName;
}
