package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Discount;
import com.mascara.electronicstoremanage.entities.Product;
import com.mascara.electronicstoremanage.utils.CurrencyUtils;
import com.mascara.electronicstoremanage.view_model.discount.DiscountViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Mapping(source = "productSet", target = "idProductList", qualifiedByName = "mapProductSetToIdProductList")
    @Mapping(source = "discountValue", target = "discountValueShow", qualifiedByName = "mapDiscountValueToDiscountValueShow")
    DiscountViewModel entityToViewModel(Discount discount);

    @Named("mapProductSetToIdProductList")
    default List<Long> mapProductSetToIdProductList(Set<Product> productSet) {
        return productSet.stream()
                .map(Product::getId)
                .collect(Collectors.toList());
    }

    @Named("mapDiscountValueToDiscountValueShow")
    default String mapDiscountValueToDiscountValueShow(Double discountValue) {
        if (discountValue > 100)
            return CurrencyUtils.getInstance().convertVietnamCurrency(discountValue);
        return discountValue.toString() + " %";
    }
}
