package com.mascara.electronicstoremanage.repositories.staff;

import com.mascara.electronicstoremanage.common.mapper.StaffMapper;
import com.mascara.electronicstoremanage.entities.Role;
import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.utils.PasswordHashingUtils;
import com.mascara.electronicstoremanage.view_model.staff.StaffCreateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffPagingRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffUpdateRequest;
import com.mascara.electronicstoremanage.view_model.staff.StaffViewModel;
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
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Long staffId = -1L;
        try {
            tx = session.beginTransaction();
            Optional<Role> roleOptional = session.createQuery("select r from Role r where r.roleName =: roleName", Role.class)
                    .setParameter("roleName", request.getRoleName())
                    .uniqueResultOptional();
            Optional<Staff> staffOptional = session.createQuery("select s from Staff s where s.phoneNumber =: phoneNumber or s.userName =: userName and s.deleted is false", Staff.class)
                    .setParameter("phoneNumber", request.getPhoneNumber())
                    .setParameter("userName", request.getUserName())
                    .uniqueResultOptional();

            if (!staffOptional.isPresent() && roleOptional.isPresent()) {
                String hashedPassword = PasswordHashingUtils.getInstance().getHash(request.getPassword());
                Staff staff = Staff.builder()
                        .fullName(request.getFullName())
                        .phoneNumber(request.getPhoneNumber())
                        .email(request.getEmail())
                        .dateOfBirth(request.getDateOfBirth())
                        .sex(request.getSex())
                        .userName(request.getUserName())
                        .password(hashedPassword)
                        .role(roleOptional.get())
                        .roleId(roleOptional.get().getId())
                        .status(request.getStatus())
                        .build();
                session.persist(staff);
                staffId = staff.getId();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return staffId;
    }

    @Override
    public boolean update(StaffUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Optional<Role> roleOptional = session.createQuery("select r from Role r where r.roleName =: roleName", Role.class)
                    .setParameter("roleName", request.getRoleName())
                    .uniqueResultOptional();
            Optional<Staff> staffOptional = session.createQuery("select s from Staff s where (s.phoneNumber =: phoneNumber or s.userName =: userName) and s.id !=: id and s.deleted is false", Staff.class)
                    .setParameter("phoneNumber", request.getPhoneNumber())
                    .setParameter("userName", request.getUserName())
                    .setParameter("id", request.getId())
                    .uniqueResultOptional();
            transaction.commit();
            if (!staffOptional.isPresent() && roleOptional.isPresent()) {
                String hashedPassword = PasswordHashingUtils.getInstance().getHash(request.getPassword());

                Staff staff = session.find(Staff.class, request.getId());
                staff.setFullName(request.getFullName());
                staff.setPhoneNumber(request.getPhoneNumber());
                staff.setEmail(request.getEmail());
                staff.setDateOfBirth(request.getDateOfBirth());
                staff.setSex(request.getSex());
                staff.setUserName(request.getUserName());
                staff.setPassword(hashedPassword);
                staff.setRole(roleOptional.get());
                staff.setRoleId(roleOptional.get().getId());
                staff.setStatus(request.getStatus());
                return HibernateUtils.merge(staff);
            }
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        Session session = HibernateUtils.getSession();
        Staff staff = session.find(Staff.class, idEntity);
        staff.setDeleted(true);
        session.close();
        return HibernateUtils.merge(staff);
    }

    @Override
    public StaffViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Staff staff = session.find(Staff.class, entityId);
        StaffViewModel staffViewModel = StaffMapper.getInstance.entityToViewModel(staff);
        return staffViewModel;
    }

    @Override
    public List<StaffViewModel> retrieveAll(StaffPagingRequest request) {
        List<StaffViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Staff", request);
        Query query = session.createQuery(cmd, Staff.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Staff> staffList = query.getResultList();

        for (Staff staff : staffList) {
            list.add(StaffMapper.getInstance.entityToViewModel(staff));
        }
        session.close();
        return list;
    }
}
