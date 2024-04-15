package com.mascara.electronicstoremanage.services.color;

import com.mascara.electronicstoremanage.view_model.color.ColorCreateRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorPagingRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorUpdateRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 14/04/2024
 * Time      : 7:18 CH
 * Filename  : ColorService
 */
public interface ColorService {
    Long insertColor(ColorCreateRequest request);

    boolean updateColor(ColorUpdateRequest request);

    boolean deleteColor(Long id);

    ColorViewModel retrieveColorById(Long id);

    List<ColorViewModel> retrieveAllColor(ColorPagingRequest request);
}
