package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Brand;
import com.mascara.electronicstoremanage.entities.Material;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;
import com.mascara.electronicstoremanage.view_model.material.MaterialViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 9:15 CH
 * Filename  : MaterialMapper
 */
@Mapper
public interface MaterialMapper {
    MaterialMapper getInstance = Mappers.getMapper(MaterialMapper.class);

    MaterialViewModel entityToViewModel(Material material);
}
