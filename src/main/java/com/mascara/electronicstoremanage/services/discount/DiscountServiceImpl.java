package com.mascara.electronicstoremanage.services.discount;

import com.mascara.electronicstoremanage.services.category.CategoryService;
import com.mascara.electronicstoremanage.view_model.discount.DiscountCreateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountPagingRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountUpdateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:34 CH
 * Filename  : DiscountServiceImpl
 */
public class DiscountServiceImpl implements DiscountService {
    private static DiscountServiceImpl instance = null;

    public static DiscountServiceImpl getInstance() {
        if (instance == null)
            instance = new DiscountServiceImpl();
        return instance;
    }

    private DiscountServiceImpl() {

    }

    @Override
    public Long insertDiscount(DiscountCreateRequest request) {
        return null;
    }

    @Override
    public boolean updateDiscount(DiscountUpdateRequest request) {
        return false;
    }

    @Override
    public boolean deleteDiscount(Long id) {
        return false;
    }

    @Override
    public DiscountViewModel retrieveDiscountById(Long id) {
        return null;
    }

    @Override
    public List<DiscountViewModel> retrieveAllDiscount(DiscountPagingRequest request) {
        return null;
    }
}
