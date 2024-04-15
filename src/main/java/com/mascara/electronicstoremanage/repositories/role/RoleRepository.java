package com.mascara.electronicstoremanage.repositories.role;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleCreateRequest;
import com.mascara.electronicstoremanage.view_model.role.RolePagingRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleUpdateRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleViewModel;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : RoleRepository
 */
public interface RoleRepository extends ModifyEntityRequest<RoleCreateRequest, RoleUpdateRequest, Long>,
        RetrieveEntityRequest<RoleViewModel, RolePagingRequest, Long> {
}
