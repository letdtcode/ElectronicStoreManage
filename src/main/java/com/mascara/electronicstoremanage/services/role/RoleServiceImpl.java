package com.mascara.electronicstoremanage.services.role;

import com.mascara.electronicstoremanage.repositories.role.RoleRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.role.RoleCreateRequest;
import com.mascara.electronicstoremanage.view_model.role.RolePagingRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleUpdateRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:36 CH
 * Filename  : RoleServiceImpl
 */
public class RoleServiceImpl implements RoleService {
    private static RoleServiceImpl instance = null;

    public static RoleServiceImpl getInstance() {
        if (instance == null)
            instance = new RoleServiceImpl();
        return instance;
    }

    private RoleServiceImpl() {

    }

    @Override
    public Long insertRole(RoleCreateRequest request) {
        return RoleRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateRole(RoleUpdateRequest request) {
        return RoleRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteRole(Long id) {
        return RoleRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public RoleViewModel retrieveRoleById(Long id) {
        return RoleRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<RoleViewModel> retrieveAllRole(RolePagingRequest request) {
        return RoleRepositoryImpl.getInstance().retrieveAll(request);
    }
}
