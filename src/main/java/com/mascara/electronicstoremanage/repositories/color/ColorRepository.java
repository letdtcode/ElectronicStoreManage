package com.mascara.electronicstoremanage.repositories.color;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandPagingRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;
import com.mascara.electronicstoremanage.view_model.color.ColorCreateRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorPagingRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorUpdateRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorViewModel;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:22 CH
 * Filename  : ColorRepository
 */
public interface ColorRepository extends ModifyEntityRequest<ColorCreateRequest, ColorUpdateRequest, Long>,
        RetrieveEntityRequest<ColorViewModel, ColorPagingRequest, Long> {
}
