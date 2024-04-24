package com.mascara.electronicstoremanage.services.discount;

import com.mascara.electronicstoremanage.repositories.discount.DiscountRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.discount.DiscountCreateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountPagingRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountUpdateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountViewModel;

import java.time.LocalDate;
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
        return DiscountRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateDiscount(DiscountUpdateRequest request) {
        return DiscountRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteDiscount(Long id) {
        return DiscountRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public boolean checkListProductCanApplyRangeDate(List<Long> idProducts, LocalDate dateStart, LocalDate dateEnd) {
        return DiscountRepositoryImpl.getInstance().checkListProductCanApplyRangeDate(idProducts, dateStart, dateEnd);
    }

    @Override
    public DiscountViewModel retrieveDiscountById(Long id) {
        return DiscountRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<DiscountViewModel> retrieveAllDiscount(DiscountPagingRequest request) {
        return DiscountRepositoryImpl.getInstance().retrieveAll(request);
    }
}
