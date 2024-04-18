package com.mascara.electronicstoremanage.repositories.product;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductCreateRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductPagingRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductUpdateRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductViewModel;
import com.mascara.electronicstoremanage.view_model.sale.ProductSaleViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : ProductRepository
 */
public interface ProductRepository extends ModifyEntityRequest<ProductCreateRequest, ProductUpdateRequest, Long>,
        RetrieveEntityRequest<ProductViewModel, ProductPagingRequest, Long> {
    List<ProductSaleViewModel> retrieveAllProductSale(ProductPagingRequest request);
}
