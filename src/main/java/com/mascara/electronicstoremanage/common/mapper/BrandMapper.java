package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Brand;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 7:15 CH
 * Filename  : BrandMapper
 */
@Mapper
public interface BrandMapper {
    BrandMapper getInstance = Mappers.getMapper(BrandMapper.class);

    BrandViewModel entityToViewModel(Brand brand);
}
