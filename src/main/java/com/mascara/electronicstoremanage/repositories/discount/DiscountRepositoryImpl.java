package com.mascara.electronicstoremanage.repositories.discount;

import com.mascara.electronicstoremanage.repositories.material.MaterialRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.discount.DiscountCreateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountPagingRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountUpdateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:22 CH
 * Filename  : DiscountRepositoryImpl
 */
public class DiscountRepositoryImpl implements DiscountRepository{
    private static DiscountRepositoryImpl instance = null;

    public static DiscountRepositoryImpl getInstance() {
        if (instance == null)
            instance = new DiscountRepositoryImpl();
        return instance;
    }

    private DiscountRepositoryImpl() {

    }
    @Override
    public Long insert(DiscountCreateRequest request) {
        return null;
    }

    @Override
    public boolean update(DiscountUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public DiscountViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<DiscountViewModel> retrieveAll(DiscountPagingRequest request) {
        return null;
    }
}
