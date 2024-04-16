package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Order;
import com.mascara.electronicstoremanage.view_model.order.OrderViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:49 CH
 * Filename  : OrderMapper
 */
@Mapper
public interface OrderMapper {
    OrderMapper getInstance = Mappers.getMapper(OrderMapper.class);

    OrderViewModel entityToViewModel(Order order);
}
