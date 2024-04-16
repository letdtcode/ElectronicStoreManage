package com.mascara.electronicstoremanage.view_model.feature;

import lombok.*;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 8:18 CH
 * Filename  : FeatureUpdateRequest
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeatureUpdateRequest {
    private Long id;
    private String featureName;
}
