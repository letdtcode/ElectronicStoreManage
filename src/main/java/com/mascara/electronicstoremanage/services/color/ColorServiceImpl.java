package com.mascara.electronicstoremanage.services.color;

import com.mascara.electronicstoremanage.repositories.color.ColorRepositoryImpl;
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
 * Filename  : ColorServiceImpl
 */
public class ColorServiceImpl implements ColorService {
    private static ColorServiceImpl instance = null;

    public static ColorServiceImpl getInstance() {
        if (instance == null)
            instance = new ColorServiceImpl();
        return instance;
    }

    private ColorServiceImpl() {

    }

    @Override
    public Long insertColor(ColorCreateRequest request) {
        return ColorRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateColor(ColorUpdateRequest request) {
        return ColorRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteColor(Long id) {
        return ColorRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public ColorViewModel retrieveColorById(Long id) {
        return ColorRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<ColorViewModel> retrieveAllColor(ColorPagingRequest request) {
        return ColorRepositoryImpl.getInstance().retrieveAll(request);
    }
}
