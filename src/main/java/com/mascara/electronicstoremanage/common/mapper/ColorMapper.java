package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Color;
import com.mascara.electronicstoremanage.view_model.color.ColorViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 9:15 CH
 * Filename  : ColorMapper
 */
@Mapper
public interface ColorMapper {
    ColorMapper getInstance = Mappers.getMapper(ColorMapper.class);

    ColorViewModel entityToViewModel(Color color);
}
