package com.mascara.electronicstoremanage.services.category;

import com.mascara.electronicstoremanage.repositories.category.CategoryRepositoryImpl;
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
        return CategoryRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateCategory(CategoryUpdateRequest request) {
        return CategoryRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteCategory(Long id) {
        return CategoryRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public CategoryViewModel retrieveCategoryById(Long id) {
        return CategoryRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<CategoryViewModel> retrieveAllCategory(CategoryPagingRequest request) {
        return CategoryRepositoryImpl.getInstance().retrieveAll(request);
    }
}
