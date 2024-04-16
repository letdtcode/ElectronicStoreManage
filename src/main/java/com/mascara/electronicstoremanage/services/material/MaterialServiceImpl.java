package com.mascara.electronicstoremanage.services.material;

import com.mascara.electronicstoremanage.repositories.material.MaterialRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.material.MaterialCreateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialPagingRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialUpdateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:35 CH
 * Filename  : MaterialServiceImpl
 */
public class MaterialServiceImpl implements MaterialService {
    private static MaterialServiceImpl instance = null;

    public static MaterialServiceImpl getInstance() {
        if (instance == null)
            instance = new MaterialServiceImpl();
        return instance;
    }

    private MaterialServiceImpl() {

    }

    @Override
    public Long insertMaterial(MaterialCreateRequest request) {
        return MaterialRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateMaterial(MaterialUpdateRequest request) {
        return MaterialRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteMaterial(Long id) {
        return MaterialRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public MaterialViewModel retrieveMaterialById(Long id) {
        return MaterialRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<MaterialViewModel> retrieveAllMaterial(MaterialPagingRequest request) {
        return MaterialRepositoryImpl.getInstance().retrieveAll(request);
    }
}
