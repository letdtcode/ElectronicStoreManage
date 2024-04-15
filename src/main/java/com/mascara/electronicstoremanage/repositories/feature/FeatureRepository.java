package com.mascara.electronicstoremanage.repositories.feature;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureCreateRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeaturePagingRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureUpdateRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureViewModel;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:23 CH
 * Filename  : FeatureRepository
 */
public interface FeatureRepository extends ModifyEntityRequest<FeatureCreateRequest, FeatureUpdateRequest, Long>,
        RetrieveEntityRequest<FeatureViewModel, FeaturePagingRequest, Long> {
}
