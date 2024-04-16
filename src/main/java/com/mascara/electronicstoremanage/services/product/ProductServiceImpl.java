package com.mascara.electronicstoremanage.services.product;

import com.mascara.electronicstoremanage.repositories.product.ProductRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.product.ProductCreateRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductPagingRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductUpdateRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:35 CH
 * Filename  : ProductServiceImpl
 */
public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl instance = null;

    public static ProductServiceImpl getInstance() {
        if (instance == null)
            instance = new ProductServiceImpl();
        return instance;
    }

    private ProductServiceImpl() {

    }

    @Override
    public Long insertProduct(ProductCreateRequest request) {
        return ProductRepositoryImpl.getInstance().insert(request);
    }

    @Override
    public boolean updateProduct(ProductUpdateRequest request) {
        return ProductRepositoryImpl.getInstance().update(request);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return ProductRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public ProductViewModel retrieveProductById(Long id) {
        return ProductRepositoryImpl.getInstance().retrieveById(id);
    }

    @Override
    public List<ProductViewModel> retrieveAllProduct(ProductPagingRequest request) {
        return ProductRepositoryImpl.getInstance().retrieveAll(request);
    }
}
