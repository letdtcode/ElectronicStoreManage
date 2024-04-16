package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Customer;
import com.mascara.electronicstoremanage.view_model.customer.CustomerViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:49 CH
 * Filename  : CustomerMapper
 */
@Mapper
public interface CustomerMapper {
    CustomerMapper getInstance = Mappers.getMapper(CustomerMapper.class);

    CustomerViewModel entityToViewModel(Customer customer);
}
