package com.mascara.electronicstoremanage.services.discount;

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
 * Filename  : DiscountService
 */
public interface DiscountService {
    Long insertDiscount(DiscountCreateRequest request);

    boolean updateDiscount(DiscountUpdateRequest request);

    boolean deleteDiscount(Long id);

    boolean checkListProductCanApplyRangeDate(List<Long> idProducts, LocalDate dateStart, LocalDate dateEnd);

    DiscountViewModel retrieveDiscountById(Long id);

    List<DiscountViewModel> retrieveAllDiscount(DiscountPagingRequest request);
}
