package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.OrderItem;
import com.mascara.electronicstoremanage.view_model.order_item.OrderItemViewModel;
import org.mapstruct.Mapper;
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
}
