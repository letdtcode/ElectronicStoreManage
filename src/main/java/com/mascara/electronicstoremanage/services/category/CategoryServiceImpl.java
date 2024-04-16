package com.mascara.electronicstoremanage.services.category;

import com.mascara.electronicstoremanage.services.auth.AuthServiceImpl;
import com.mascara.electronicstoremanage.view_model.category.CategoryCreateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryPagingRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryUpdateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 09/04/2024
 * Time      : 7:45 CH
 * Filename  : CategoryServiceImpl
 */
public class CategoryServiceImpl implements CategoryService {

    private static CategoryServiceImpl instance = null;

    public static CategoryServiceImpl getInstance() {
        if (instance == null)
            instance = new CategoryServiceImpl();
        return instance;
    }

    private CategoryServiceImpl() {

    }

    @Override
    public Long insertCategory(CategoryCreateRequest request) {
        return null;
    }

    @Override
    public boolean updateCategory(CategoryUpdateRequest request) {
        return false;
    }

    @Override
    public boolean deleteCategory(Long id) {
        return false;
    }

    @Override
    public CategoryViewModel retrieveCategoryById(Long id) {
        return null;
    }

    @Override
    public List<CategoryViewModel> retrieveAllCategory(CategoryPagingRequest request) {
        return null;
    }
}
