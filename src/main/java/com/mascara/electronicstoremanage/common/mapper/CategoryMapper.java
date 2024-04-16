package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Category;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:49 CH
 * Filename  : CategoryMapper
 */
@Mapper
public interface CategoryMapper {
    CategoryMapper getInstance = Mappers.getMapper(CategoryMapper.class);

    CategoryViewModel entityToViewModel(Category category);
}
