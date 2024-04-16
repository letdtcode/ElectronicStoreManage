package com.mascara.electronicstoremanage.services.material;

import com.mascara.electronicstoremanage.view_model.material.MaterialCreateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialPagingRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialUpdateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:34 CH
 * Filename  : MaterialService
 */
public interface MaterialService {
    Long insertMaterial(MaterialCreateRequest request);

    boolean updateMaterial(MaterialUpdateRequest request);

    boolean deleteMaterial(Long id);

    MaterialViewModel retrieveMaterialById(Long id);

    List<MaterialViewModel> retrieveAllMaterial(MaterialPagingRequest request);
}
