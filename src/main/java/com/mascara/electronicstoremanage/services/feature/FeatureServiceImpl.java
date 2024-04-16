package com.mascara.electronicstoremanage.services.feature;

import com.mascara.electronicstoremanage.repositories.feature.FeatureRepositoryImpl;
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
 * Filename  : FeatureServiceImpl
 */
public class FeatureServiceImpl implements FeatureService {
    private static FeatureServiceImpl instance = null;

    public static FeatureServiceImpl getInstance() {
        if (instance == null)
            instance = new FeatureServiceImpl();
        return instance;
    }

    private FeatureServiceImpl() {

    }

    @Override
    public Long insertFeature(FeatureCreateRequest request) {
        return FeatureRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateFeature(FeatureUpdateRequest request) {
        return FeatureRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteFeature(Long id) {
        return FeatureRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public FeatureViewModel retrieveFeatureById(Long id) {
        return FeatureRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<FeatureViewModel> retrieveAllFeature(FeaturePagingRequest request) {
        return FeatureRepositoryImpl.getInstance().retrieveAll(request);
    }
}
