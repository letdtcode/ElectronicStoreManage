package com.mascara.electronicstoremanage.repositories.role;

import com.mascara.electronicstoremanage.common.mapper.RoleMapper;
import com.mascara.electronicstoremanage.entities.Brand;
import com.mascara.electronicstoremanage.entities.Role;
import com.mascara.electronicstoremanage.entities.Staff;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.role.RoleCreateRequest;
import com.mascara.electronicstoremanage.view_model.role.RolePagingRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleUpdateRequest;
import com.mascara.electronicstoremanage.view_model.role.RoleViewModel;
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
 * Filename  : RoleRepositoryImpl
 */
public class RoleRepositoryImpl implements RoleRepository {
    private static RoleRepositoryImpl instance = null;

    public static RoleRepositoryImpl getInstance() {
        if (instance == null)
            instance = new RoleRepositoryImpl();
        return instance;
    }

    private RoleRepositoryImpl() {

    }

    @Override
    public Long insert(RoleCreateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Role role = Role.builder()
                .roleName(request.getRoleName())
                .build();
        Long roleId = -1L;
        try {
            tx = session.beginTransaction();
            Optional<Role> roleOptional = session.createQuery("select r from Role r where r.roleName =: roleName and r.deleted is false", Role.class)
                    .setParameter("roleName", request.getRoleName()).uniqueResultOptional();
            if (!roleOptional.isPresent()) {
                session.persist(role);
                roleId = role.getId();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return roleId;
    }

    @Override
    public boolean update(RoleUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Optional<Role> roleOptional = session.createQuery("select r from Role r where r.roleName =: roleName and id != :id and r.deleted is false", Role.class)
                    .setParameter("roleName", request.getRoleName())
                    .setParameter("id", request.getId())
                    .uniqueResultOptional();
            if (!roleOptional.isPresent()) {
                Role role = session.find(Role.class, request.getId());
                role.setRoleName(request.getRoleName());
                transaction.commit();
                return HibernateUtils.merge(role);
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
        Role role = session.find(Role.class, idEntity);
        Query query = session.createQuery("select s from Staff s where s.roleId =: roleId and s.deleted is false", Staff.class);
        query.setParameter("roleId", role.getId());
        Optional<Staff> staffOptional = query.uniqueResultOptional();
        if (staffOptional.isPresent())
            return false;
        role.setDeleted(true);
        session.close();
        return HibernateUtils.merge(role);
    }

    @Override
    public RoleViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Role role = session.find(Role.class, entityId);
        RoleViewModel roleViewModel = RoleMapper.getInstance.entityToViewModel(role);
        return roleViewModel;
    }

    @Override
    public List<RoleViewModel> retrieveAll(RolePagingRequest request) {
        List<RoleViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Role", request);
        Query query = session.createQuery(cmd, Brand.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Role> roleList = query.getResultList();

        for (Role role : roleList) {
            list.add(RoleMapper.getInstance.entityToViewModel(role));
        }
        session.close();
        return list;
    }
}
