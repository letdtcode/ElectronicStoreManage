package com.mascara.electronicstoremanage.repositories.category;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandCreateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandPagingRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandUpdateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;
import com.mascara.electronicstoremanage.view_model.category.CategoryCreateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryPagingRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryUpdateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:22 CH
 * Filename  : CategoryRepository
 */
public interface CategoryRepository extends ModifyEntityRequest<CategoryCreateRequest, CategoryUpdateRequest, Long>,
        RetrieveEntityRequest<CategoryViewModel, CategoryPagingRequest, Long> {
}
