package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Color;
import com.mascara.electronicstoremanage.entities.Feature;
import com.mascara.electronicstoremanage.entities.Product;
import com.mascara.electronicstoremanage.view_model.product.ProductViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:49 CH
 * Filename  : ProductMapper
 */
@Mapper
public interface ProductMapper {
    ProductMapper getInstance = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "brand.brandName", target = "brandName")
    @Mapping(source = "material.materialName", target = "materialName")
    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(source = "featureSet", target = "featureNameList", qualifiedByName = "mapFeatureSetToList")
    @Mapping(source = "featureSet", target = "featureNameListShow", qualifiedByName = "mapFeatureSetToStringShow")
    @Mapping(source = "colorSet", target = "colorNameList", qualifiedByName = "mapColorSetToList")
    @Mapping(source = "colorSet", target = "colorNameListShow", qualifiedByName = "mapColorSetToStringShow")
    ProductViewModel entityToViewModel(Product product);

    @Named("mapFeatureSetToList")
    default List<String> mapFeatureSetToList(Set<Feature> featureSet) {
        return featureSet.stream()
                .map(Feature::getFeatureName)
                .collect(Collectors.toList());
    }

    @Named("mapFeatureSetToStringShow")
    default String mapFeatureSetToStringShow(Set<Feature> featureSet) {
        StringBuilder sb = new StringBuilder();
        for (Feature feature : featureSet) {
            sb.append(feature.getFeatureName() + " - ");
        }
        return sb.toString();
    }

    @Named("mapColorSetToList")
    default List<String> mapColorSetToList(Set<Color> colorSet) {
        return colorSet.stream()
                .map(Color::getColorName)
                .collect(Collectors.toList());
    }

    @Named("mapColorSetToStringShow")
    default String mapColorSetToStringShow(Set<Color> colorSet) {
        StringBuilder sb = new StringBuilder();
        for (Color color : colorSet) {
            sb.append(color.getColorName() + " - ");
        }
        return sb.toString();
    }
}
