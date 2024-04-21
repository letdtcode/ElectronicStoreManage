package com.mascara.electronicstoremanage.repositories.discount;

import com.mascara.electronicstoremanage.entities.Discount;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.discount.DiscountCreateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountPagingRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountUpdateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountViewModel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:22 CH
 * Filename  : DiscountRepositoryImpl
 */
public class DiscountRepositoryImpl implements DiscountRepository {
    private static DiscountRepositoryImpl instance = null;

    public static DiscountRepositoryImpl getInstance() {
        if (instance == null)
            instance = new DiscountRepositoryImpl();
        return instance;
    }

    private DiscountRepositoryImpl() {

    }

    @Override
    public Long insert(DiscountCreateRequest request) {
        return null;
    }

    @Override
    public boolean update(DiscountUpdateRequest request) {
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        return false;
    }

    @Override
    public DiscountViewModel retrieveById(Long entityId) {
        return null;
    }

    @Override
    public List<DiscountViewModel> retrieveAll(DiscountPagingRequest request) {
        return null;
    }

    @Override
    public Optional<Discount> getDiscountCurrentByProductId(Long productId) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Optional<Discount> discount = session.createQuery("select d from Discount d join d.productSet ps where d.dateEnd >= CURRENT_DATE and d.deleted is false and ps.id =: id", Discount.class)
                    .setParameter("id", productId)
                    .uniqueResultOptional();
            return discount;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Optional.empty();
    }
}
