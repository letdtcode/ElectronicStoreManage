package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Feature;
import com.mascara.electronicstoremanage.view_model.feature.FeatureViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 9:15 CH
 * Filename  : FeatureMappe
 */
@Mapper
public interface FeatureMapper {
    FeatureMapper getInstance = Mappers.getMapper(FeatureMapper.class);

    FeatureViewModel entityToViewModel(Feature feature);
}
