package com.mascara.electronicstoremanage.repositories.material;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialCreateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialPagingRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialUpdateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialViewModel;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:23 CH
 * Filename  : MaterialRepository
 */
public interface MaterialRepository extends ModifyEntityRequest<MaterialCreateRequest, MaterialUpdateRequest, Long>,
        RetrieveEntityRequest<MaterialViewModel, MaterialPagingRequest, Long> {
}
