package com.mascara.electronicstoremanage.repositories.customer;

import com.mascara.electronicstoremanage.common.mapper.CustomerMapper;
import com.mascara.electronicstoremanage.entities.Customer;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.customer.CustomerCreateRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerPagingRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerUpdateRequest;
import com.mascara.electronicstoremanage.view_model.customer.CustomerViewModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:22 CH
 * Filename  : CustomerRepositoryImpl
 */
public class CustomerRepositoryImpl implements CustomerRepository {
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
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Customer customer = Customer.builder()
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .address(request.getAddress())
                .sex(request.getSex())
                .build();
        Long customerId = -1L;
        try {
            tx = session.beginTransaction();
            Optional<Customer> customerOptional = session.createQuery("select c from Customer c where c.phoneNumber =: phoneNumber", Customer.class)
                    .setParameter("phoneNumber", request.getPhoneNumber()).uniqueResultOptional();
            if (!customerOptional.isPresent()) {
                session.persist(customer);
                customerId = customer.getId();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customerId;
    }

    @Override
    public boolean update(CustomerUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        try {
            Optional<Customer> customerOptional = session.createQuery("select c from Customer c where c.phoneNumber =: phoneNumber and id != :id", Customer.class)
                    .setParameter("phoneNumber", request.getPhoneNumber())
                    .setParameter("id", request.getId())
                    .uniqueResultOptional();
            if (!customerOptional.isPresent()) {
                Customer customer = session.find(Customer.class, request.getId());
                customer.setFullName(request.getFullName());
                customer.setPhoneNumber(request.getPhoneNumber());
                customer.setEmail(request.getEmail());
                customer.setAddress(request.getAddress());
                customer.setSex(request.getSex());
                return HibernateUtils.merge(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        Session session = HibernateUtils.getSession();
        Customer customer = session.find(Customer.class, idEntity);
        customer.setDeleted(true);
        session.close();
        return HibernateUtils.merge(customer);
    }

    @Override
    public CustomerViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Customer customer = session.find(Customer.class, entityId);
        CustomerViewModel customerViewModel = CustomerMapper.getInstance.entityToViewModel(customer);
        return customerViewModel;
    }

    @Override
    public List<CustomerViewModel> retrieveAll(CustomerPagingRequest request) {
        List<CustomerViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Customer", request);
        Query query = session.createQuery(cmd, Customer.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Customer> customerList = query.getResultList();

        for (Customer customer : customerList) {
            list.add(CustomerMapper.getInstance.entityToViewModel(customer));
        }
        session.close();
        return list;
    }
}
