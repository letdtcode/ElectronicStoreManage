package com.mascara.electronicstoremanage.services.brand;

import com.mascara.electronicstoremanage.repositories.brand.BrandRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.brand.BrandCreateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandPagingRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandUpdateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 13/04/2024
 * Time      : 7:25 CH
 * Filename  : BrandServiceImpl
 */
public class BrandServiceImpl implements BrandService {
    private static BrandServiceImpl instance = null;

    public static BrandServiceImpl getInstance() {
        if (instance == null)
            instance = new BrandServiceImpl();
        return instance;
    }

    private BrandServiceImpl() {

    }

    @Override
    public Long insertBrand(BrandCreateRequest request) {
        return BrandRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateBrand(BrandUpdateRequest request) {
        return BrandRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteBrand(Long id) {
        return BrandRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public BrandViewModel retrieveBrandById(Long id) {
        return BrandRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<BrandViewModel> retrieveAllBrand(BrandPagingRequest request) {
        return BrandRepositoryImpl.getInstance().retrieveAll(request);
    }
}
