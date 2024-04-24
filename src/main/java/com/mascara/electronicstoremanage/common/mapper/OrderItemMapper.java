package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.OrderItem;
import com.mascara.electronicstoremanage.utils.CurrencyUtils;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemViewModel;
import com.mascara.electronicstoremanage.view_model.sale.CartItemViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

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

    OrderItemViewModel entityToViewModel(OrderItem orderItem);

    @Mapping(source = "product.productName", target = "productName")
    @Mapping(source = "unitPrice", target = "unitPriceShow", qualifiedByName = "mapToCurrencyVietnam")
    CartItemViewModel entityToCartItemViewModel(OrderItem orderItem);

    @Named("mapToCurrencyVietnam")
    default String mapToCurrencyVietnam(Double money) {
        return CurrencyUtils.getInstance().convertVietnamCurrency(money);
    }
}
