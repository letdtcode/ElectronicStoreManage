package com.mascara.electronicstoremanage.services.customer;

import com.mascara.electronicstoremanage.view_model.customer.CustomerCreateRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerPagingRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerUpdateRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 16/04/2024
 * Time      : 6:34 CH
 * Filename  : CustomerService
 */
public interface CustomerService {
    Long insertCustomer(CustomerCreateRequest request);

    boolean updateCustomer(CustomerUpdateRequest request);

    boolean deleteCustomer(Long id);

    CustomerViewModel retrieveCustomerById(Long id);

    List<CustomerViewModel> retrieveAllCustomer(CustomerPagingRequest request);
}
