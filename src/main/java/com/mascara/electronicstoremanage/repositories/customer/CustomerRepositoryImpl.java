package com.mascara.electronicstoremanage.repositories.customer;

import com.mascara.electronicstoremanage.repositories.material.MaterialRepositoryImpl;
import com.mascara.electronicstoremanage.view_model.customer.CustomerCreateRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerPagingRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerUpdateRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerViewModel;

import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:22 CH
 * Filename  : CustomerRepositoryImpl
 */
public class CustomerRepositoryImpl implements CustomerRepository{
    private static CustomerRepositoryImpl instance = null;

    public static CustomerRepositoryImpl getInstance() {
        if (instance == null)
            instance = new CustomerRepositoryImpl();
        return instance;
    }

    private CustomerRepositoryImpl() {

    }
    @Override
    public Long insert(CustomerCreateRequest request) {
        return null;
    }

    @Override
    public boolean update(CustomerUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public CustomerViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<CustomerViewModel> retrieveAll(CustomerPagingRequest request) {
        return null;
    }
}
