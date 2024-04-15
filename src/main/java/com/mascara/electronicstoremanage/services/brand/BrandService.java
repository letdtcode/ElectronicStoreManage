package com.mascara.electronicstoremanage.services.brand;

import com.mascara.electronicstoremanage.view_model.brand.BrandCreateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandPagingRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandUpdateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 13/04/2024
 * Time      : 7:09 CH
 * Filename  : BrandService
 */
public interface BrandService {
    Long insertBrand(BrandCreateRequest request);

    boolean updateBrand(BrandUpdateRequest request);

    boolean deleteBrand(Long id);

    BrandViewModel retrieveBrandById(Long id);

    List<BrandViewModel> retrieveAllBrand(BrandPagingRequest request);
}
