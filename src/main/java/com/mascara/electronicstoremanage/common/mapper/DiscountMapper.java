package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Discount;
import com.mascara.electronicstoremanage.view_model.discount.DiscountViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:49 CH
 * Filename  : DiscountMapper
 */
@Mapper
public interface DiscountMapper {
    DiscountMapper getInstance = Mappers.getMapper(DiscountMapper.class);

    DiscountViewModel entityToViewModel(Discount discount);
}
