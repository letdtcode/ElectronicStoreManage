package com.mascara.electronicstoremanage.repositories.product;

import com.mascara.electronicstoremanage.repositories.material.MaterialRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.product.ProductCreateRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductPagingRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductUpdateRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : ProductRepositoryImpl
 */
public class ProductRepositoryImpl implements ProductRepository{
    private static ProductRepositoryImpl instance = null;

    public static ProductRepositoryImpl getInstance() {
        if (instance == null)
            instance = new ProductRepositoryImpl();
        return instance;
    }

    private ProductRepositoryImpl() {

    }
    @Override
    public Long insert(ProductCreateRequest request) {
        return null;
    }

    @Override
    public boolean update(ProductUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public ProductViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<ProductViewModel> retrieveAll(ProductPagingRequest request) {
        return null;
    }
}
