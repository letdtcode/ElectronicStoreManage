package com.mascara.electronicstoremanage.services.feature;

import com.mascara.electronicstoremanage.view_model.feature.FeatureCreateRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeaturePagingRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureUpdateRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:34 CH
 * Filename  : FeatureService
 */
public interface FeatureService {
    Long insertFeature(FeatureCreateRequest request);

    boolean updateFeature(FeatureUpdateRequest request);

    boolean deleteFeature(Long id);

    FeatureViewModel retrieveFeatureById(Long id);

    List<FeatureViewModel> retrieveAllFeature(FeaturePagingRequest request);
}
