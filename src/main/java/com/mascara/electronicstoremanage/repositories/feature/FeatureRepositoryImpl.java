package com.mascara.electronicstoremanage.repositories.feature;

import com.mascara.electronicstoremanage.repositories.material.MaterialRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.feature.FeatureCreateRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeaturePagingRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureUpdateRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:23 CH
 * Filename  : FeatureRepositoryImpl
 */
public class FeatureRepositoryImpl implements FeatureRepository{
    private static FeatureRepositoryImpl instance = null;

    public static FeatureRepositoryImpl getInstance() {
        if (instance == null)
            instance = new FeatureRepositoryImpl();
        return instance;
    }

    private FeatureRepositoryImpl() {

    }
    @Override
    public Long insert(FeatureCreateRequest request) {
        return null;
    }

    @Override
    public boolean update(FeatureUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public FeatureViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<FeatureViewModel> retrieveAll(FeaturePagingRequest request) {
        return null;
    }
}
