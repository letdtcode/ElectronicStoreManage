package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.view_model.staff.StaffViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:50 CH
 * Filename  : StaffMapper
 */
@Mapper
public interface StaffMapper {
    StaffMapper getInstance = Mappers.getMapper(StaffMapper.class);

    StaffViewModel entityToViewModel(Staff staff);
}
