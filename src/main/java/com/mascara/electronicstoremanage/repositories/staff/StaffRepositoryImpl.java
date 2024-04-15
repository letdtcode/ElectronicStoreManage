package com.mascara.electronicstoremanage.repositories.staff;

import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.staff.StaffCreateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffPagingRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffUpdateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffViewModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : StaffRepositoryImpl
 */
public class StaffRepositoryImpl implements StaffRepository {
    private static StaffRepositoryImpl instance = null;

    public static StaffRepositoryImpl getInstance() {
        if (instance == null)
            instance = new StaffRepositoryImpl();
        return instance;
    }

    private StaffRepositoryImpl() {

    }

    public Optional<Staff> findByUserName(String userName) {
        Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select Staff from Staff where userName =: s1", Staff.class);
        query.setParameter("s1", userName);
        Optional<Staff> staff = query.uniqueResultOptional();
        tx.commit();
        session.close();
        return staff;
    }

    @Override
    public Long insert(StaffCreateRequest request) {
        return null;
    }

    @Override
    public boolean update(StaffUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public StaffViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<StaffViewModel> retrieveAll(StaffPagingRequest request) {
        return null;
    }
}
