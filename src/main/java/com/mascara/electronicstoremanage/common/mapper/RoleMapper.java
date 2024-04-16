package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Role;
import com.mascara.electronicstoremanage.view_model.role.RoleViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:50 CH
 * Filename  : RoleMapper
 */
@Mapper
public interface RoleMapper {
    RoleMapper getInstance = Mappers.getMapper(RoleMapper.class);

    RoleViewModel entityToViewModel(Role role);
}
