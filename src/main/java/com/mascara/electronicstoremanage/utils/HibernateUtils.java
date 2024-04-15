package com.mascara.electronicstoremanage.utils;

import com.mascara.electronicstoremanage.common.paging.PagingRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:17 CH
 * Filename  : HibernateUtils
 */
public class HibernateUtils {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static void buildTable() {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        return configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        sessionFactory.close();
    }

    public static boolean merge(Object entity) {
        boolean isSucceed = true;
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
            isSucceed = false;
        } finally {
            session.close();
        }
        return isSucceed;
    }

    public static boolean remove(Object entity) {
        boolean isSucceed = true;
        Session session = getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            isSucceed = false;
        } finally {
            session.close();
        }
        return isSucceed;
    }

    public static Long count(String table, String condition) {
        Session session = getSession();

        String query = "SELECT COUNT(*) FROM " + table;
        if (condition != null && !condition.equals("")) {
            query += " where " + condition;
        }
        Long count = (Long) session
                .createQuery(query)
                .getSingleResult();

        return count;
    }

    public static String getRetrieveAllQuery(String tableName, PagingRequest req) {
        String cmd = "from " + tableName;
        boolean haveCondition = false;
        if (req.getKeyword() != null && !req.getKeyword().equals("") && req.getColumnName() != null && (long) req.getColumnName().size() > 0) {
            haveCondition = true;
            String list = "";
            for (String c : req.getColumnName()) {
                list += c + " like '%" + req.getKeyword() + "%' or ";
            }
            list = list.substring(0, list.lastIndexOf("or "));
            cmd += " where " + list;
            if (req.getCondition() != null && !req.getCondition().equals("")) {
                cmd += " and " + req.getCondition();
            }
        } else if (req.getCondition() != null && !req.getCondition().equals("")) {
            haveCondition = true;
            cmd += " where " + req.getCondition();
        }
        if (haveCondition == false) {
            cmd += " where deleted is false ";
        } else {
            cmd += " and deleted is false";
        }
        if (req.getSortBy() != null && !req.getSortBy().equals(""))
            cmd += " order by " + req.getSortBy() + " " + req.getTypeSort();
        return cmd;
    }
}
