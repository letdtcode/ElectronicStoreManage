package com.mascara.electronicstoremanage.services.category;

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
 * Filename  : CategoryService
 */
public interface CategoryService {
    Long insertCategory(CategoryCreateRequest request);

    boolean updateCategory(CategoryUpdateRequest request);

    boolean deleteCategory(Long id);

    CategoryViewModel retrieveCategoryById(Long id);

    List<CategoryViewModel> retrieveAllCategory(CategoryPagingRequest request);
}
