package com.mascara.electronicstoremanage.repositories.discount;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.entities.Discount;
import com.mascara.electronicstoremanage.view_model.discount.DiscountCreateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountPagingRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountUpdateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountViewModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:22 CH
 * Filename  : DiscountRepository
 */
public interface DiscountRepository extends ModifyEntityRequest<DiscountCreateRequest, DiscountUpdateRequest, Long>,
        RetrieveEntityRequest<DiscountViewModel, DiscountPagingRequest, Long> {
    Optional<Discount> getDiscountCurrentByProductId(Long productId);

    boolean checkListProductCanApplyRangeDate(List<Long> productIds, LocalDate dateStart, LocalDate dateEnd, Long idDiscountUpdate);
}
