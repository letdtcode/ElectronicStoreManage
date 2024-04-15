package com.mascara.electronicstoremanage.repositories.brand;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandCreateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandPagingRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandUpdateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:21 CH
 * Filename  : BrandRepository
 */
public interface BrandRepository extends ModifyEntityRequest<BrandCreateRequest, BrandUpdateRequest, Long>,
        RetrieveEntityRequest<BrandViewModel, BrandPagingRequest, Long> {
}
