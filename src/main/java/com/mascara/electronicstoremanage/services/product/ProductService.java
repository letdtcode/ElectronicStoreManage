package com.mascara.electronicstoremanage.services.product;

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
 * Filename  : ProductService
 */
public interface ProductService {
    Long insertProduct(ProductCreateRequest request);

    boolean updateProduct(ProductUpdateRequest request);

    boolean deleteProduct(Long id);

    ProductViewModel retrieveProductById(Long id);

    List<ProductViewModel> retrieveAllProduct(ProductPagingRequest request);
}
