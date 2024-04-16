package com.mascara.electronicstoremanage.services.customer;

import com.mascara.electronicstoremanage.services.category.CategoryService;
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
 * Filename  : CustomerServiceImpl
 */
public class CustomerServiceImpl implements CustomerService {
    private static CustomerServiceImpl instance = null;

    public static CustomerServiceImpl getInstance() {
        if (instance == null)
            instance = new CustomerServiceImpl();
        return instance;
    }

    private CustomerServiceImpl() {

    }

    @Override
    public Long insertCustomer(CustomerCreateRequest request) {
        return null;
    }

    @Override
    public boolean updateCustomer(CustomerUpdateRequest request) {
        return false;
    }

    @Override
    public boolean deleteCustomer(Long id) {
        return false;
    }

    @Override
    public CustomerViewModel retrieveCustomerById(Long id) {
        return null;
    }

    @Override
    public List<CustomerViewModel> retrieveAllCustomer(CustomerPagingRequest request) {
        return null;
    }
}
