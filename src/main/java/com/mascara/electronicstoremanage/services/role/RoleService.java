package com.mascara.electronicstoremanage.services.role;

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
 * Filename  : RoleService
 */
public interface RoleService {
    Long insertRole(RoleCreateRequest request);

    boolean updateRole(RoleUpdateRequest request);

    boolean deleteRole(Long id);

    RoleViewModel retrieveRoleById(Long id);

    List<RoleViewModel> retrieveAllRole(RolePagingRequest request);
}
