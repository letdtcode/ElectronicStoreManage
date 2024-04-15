package com.mascara.electronicstoremanage.repositories.category;

import com.mascara.electronicstoremanage.repositories.material.MaterialRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.category.CategoryCreateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryPagingRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryUpdateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:22 CH
 * Filename  : CategoryRepositoryImpl
 */
public class CategoryRepositoryImpl implements CategoryRepository{
    private static CategoryRepositoryImpl instance = null;

    public static CategoryRepositoryImpl getInstance() {
        if (instance == null)
            instance = new CategoryRepositoryImpl();
        return instance;
    }

    private CategoryRepositoryImpl() {

    }
    @Override
    public Long insert(CategoryCreateRequest request) {
        return null;
    }

    @Override
    public boolean update(CategoryUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public CategoryViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<CategoryViewModel> retrieveAll(CategoryPagingRequest request) {
        return null;
    }
}
