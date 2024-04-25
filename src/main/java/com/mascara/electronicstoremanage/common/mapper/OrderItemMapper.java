package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Discount;
import com.mascara.electronicstoremanage.entities.OrderItem;
import com.mascara.electronicstoremanage.enums.discount.TypeDiscountEnum;
import com.mascara.electronicstoremanage.repositories.discount.DiscountRepositoryImpl;
import com.mascara.electronicstoremanage.utils.CurrencyUtils;
import com.mascara.electronicstoremanage.view_model.order.OrderItemViewModel;
import com.mascara.electronicstoremanage.view_model.sale.CartItemViewModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:49 CH
 * Filename  : OrderItemMapper
 */
@Mapper
public interface OrderItemMapper {
    OrderItemMapper getInstance = Mappers.getMapper(OrderItemMapper.class);

    @Mapping(source = "product.productName", target = "productName")
    @Mapping(target = "totalItem", expression = "java(orderItem.getQuantity() * orderItem.getUnitPrice())")
    @Mapping(source = "unitPrice", target = "unitPriceShow", qualifiedByName = "mapToCurrencyVietnam")
    @Mapping(target = "totalItemShow", expression = "java(convertVietnamCurrency(orderItem.getUnitPrice(),orderItem.getQuantity()))")
    OrderItemViewModel entityToViewModel(OrderItem orderItem, @Context Double unitPrice, @Context Integer quantity);

    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "unitPrice", target = "unitPriceShow", qualifiedByName = "mapToCurrencyVietnam")
    @Mapping(source = "productId", target = "discountValue", qualifiedByName = "mapProductIdToDiscountValue")
    @Mapping(source = "productId", target = "typeDiscount", qualifiedByName = "mapProductIdToTypeDiscount")
    CartItemViewModel entityToCartItemViewModel(OrderItem orderItem);

    @Named("convertVietnamCurrency")
    default String convertVietnamCurrency(Double unitPrice, Integer quantity) {
        return CurrencyUtils.getInstance().convertVietnamCurrency(unitPrice * quantity);
    }

    @Named("mapToCurrencyVietnam")
    default String mapToCurrencyVietnam(Double money) {
        return CurrencyUtils.getInstance().convertVietnamCurrency(money);
    }

    @Named("mapProductIdToDiscountValue")
    default Double mapProductIdToDiscountValue(Long productId) {
        Optional<Discount> discountOptional = DiscountRepositoryImpl.getInstance().getDiscountCurrentByProductId(productId);
        if (discountOptional.isPresent()) {
            Discount discount = discountOptional.get();
            return discount.getDiscountValue();
        }
        return null;
    }

    @Named("mapProductIdToTypeDiscount")
    default TypeDiscountEnum mapProductIdToTypeDiscount(Long productId) {
        Optional<Discount> discountOptional = DiscountRepositoryImpl.getInstance().getDiscountCurrentByProductId(productId);
        if (discountOptional.isPresent()) {
            Discount discount = discountOptional.get();
            return discount.getTypeDiscount();
        }
        return null;
    }
}
