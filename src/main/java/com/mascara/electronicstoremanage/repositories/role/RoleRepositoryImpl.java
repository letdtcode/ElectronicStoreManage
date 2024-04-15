package com.mascara.electronicstoremanage.repositories.role;

import com.mascara.electronicstoremanage.repositories.material.MaterialRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.role.RoleCreateRequest;
import com.mascara.electronicstoremanage.view_model.role.RolePagingRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleUpdateRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : RoleRepositoryImpl
 */
public class RoleRepositoryImpl implements RoleRepository{
    private static RoleRepositoryImpl instance = null;

    public static RoleRepositoryImpl getInstance() {
        if (instance == null)
            instance = new RoleRepositoryImpl();
        return instance;
    }

    private RoleRepositoryImpl() {

    }
    @Override
    public Long insert(RoleCreateRequest request) {
        return null;
    }

    @Override
    public boolean update(RoleUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public RoleViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<RoleViewModel> retrieveAll(RolePagingRequest request) {
        return null;
    }
}
