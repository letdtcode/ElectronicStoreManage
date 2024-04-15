package com.mascara.electronicstoremanage.repositories.material;

import com.mascara.electronicstoremanage.repositories.brand.BrandRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.material.MaterialCreateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialPagingRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialUpdateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:23 CH
 * Filename  : MaterialRepositoryImpl
 */
public class MaterialRepositoryImpl implements MaterialRepository {
    private static MaterialRepositoryImpl instance = null;

    public static MaterialRepositoryImpl getInstance() {
        if (instance == null)
            instance = new MaterialRepositoryImpl();
        return instance;
    }

    private MaterialRepositoryImpl() {

    }

    @Override
    public Long insert(MaterialCreateRequest request) {
        return null;
    }

    @Override
    public boolean update(MaterialUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public MaterialViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<MaterialViewModel> retrieveAll(MaterialPagingRequest request) {
        return null;
    }
}
