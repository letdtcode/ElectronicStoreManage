package com.mascara.electronicstoremanage.repositories.customer;

import com.mascara.electronicstoremanage.common.interfaces.ModifyEntityRequest;
import com.mascara.electronicstoremanage.common.interfaces.RetrieveEntityRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerCreateRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerPagingRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerUpdateRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerViewModel;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:22 CH
 * Filename  : CustomerRepository
 */
public interface CustomerRepository extends ModifyEntityRequest<CustomerCreateRequest, CustomerUpdateRequest, Long>,
        RetrieveEntityRequest<CustomerViewModel, CustomerPagingRequest, Long> {
}
