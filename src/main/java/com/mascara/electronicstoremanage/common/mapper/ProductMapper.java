package com.mascara.electronicstoremanage.common.mapper;

import com.mascara.electronicstoremanage.entities.Color;
import com.mascara.electronicstoremanage.entities.Discount;
import com.mascara.electronicstoremanage.entities.Feature;
import com.mascara.electronicstoremanage.entities.Product;
import com.mascara.electronicstoremanage.repositories.discount.DiscountRepositoryImpl;
import com.mascara.electronicstoremanage.utils.CurrencyUtils;
import com.mascara.electronicstoremanage.view_model.discount.ProductApplyViewModel;
import com.mascara.electronicstoremanage.view_model.product.ProductViewModel;
import com.mascara.electronicstoremanage.view_model.sale.ProductSaleViewModel;
import com.mascara.electronicstoremanage.view_model.statistic.ProductStatisticViewModel;
import javafx.scene.control.CheckBox;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;
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
    @Mapping(source = "importPrice", target = "importPriceShow", qualifiedByName = "mapToCurrencyVietnam")
    @Mapping(source = "salePrice", target = "salePriceShow", qualifiedByName = "mapToCurrencyVietnam")
    ProductViewModel entityToViewModel(Product product);

    @Mapping(source = "brand.brandName", target = "brandName")
    @Mapping(source = "material.materialName", target = "materialName")
    @Mapping(source = "category.categoryName", target = "categoryName")
    @Mapping(source = "colorSet", target = "colorNameList", qualifiedByName = "mapColorSetToList")
    @Mapping(source = "colorSet", target = "colorNameListShow", qualifiedByName = "mapColorSetToStringShow")
    @Mapping(source = "id", target = "discountValueShow", qualifiedByName = "mapProductIdToDiscountValueShow")
    @Mapping(source = "salePrice", target = "salePriceShow", qualifiedByName = "mapToCurrencyVietnam")
    ProductSaleViewModel entityToSaleViewModel(Product product);

    @Mapping(source = "id", target = "checkBox", qualifiedByName = "mapidProductToCheckBox")
    @Mapping(source = "salePrice", target = "salePriceShow", qualifiedByName = "mapToCurrencyVietnam")
    ProductApplyViewModel entityToApplyViewModel(Product product);

    ProductStatisticViewModel entityToStatisticViewModel(Product product);

    @Named("mapidProductToCheckBox")
    default CheckBox mapidProductToCheckBox(Long id) {
        return new CheckBox();
    }


    @Named("mapFeatureSetToList")
    default List<String> mapFeatureSetToList(Set<Feature> featureSet) {
        return featureSet.stream()
                .map(Feature::getFeatureName)
                .collect(Collectors.toList());
    }

    @Named("mapToCurrencyVietnam")
    default String mapToCurrencyVietnam(Double money) {
        return CurrencyUtils.getInstance().convertVietnamCurrency(money);
    }

    @Named("mapProductIdToDiscountValueShow")
    default String mapProductIdToDiscountValueShow(Long productId) {
        String result = "Không áp dụng";
        Optional<Discount> discountOptional = DiscountRepositoryImpl.getInstance().getDiscountCurrentByProductId(productId);
        if (discountOptional.isPresent()) {
            Discount discount = discountOptional.get();
            switch (discount.getTypeDiscount()) {
                case CASH -> result = discount.getDiscountValue().toString() + " VND";
                case PERCENT -> result = discount.getDiscountValue().toString() + " %";
            }
        }
        return result;
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
