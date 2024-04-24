package com.mascara.electronicstoremanage.repositories.discount;

import com.mascara.electronicstoremanage.common.mapper.DiscountMapper;
import com.mascara.electronicstoremanage.entities.Customer;
import com.mascara.electronicstoremanage.entities.Discount;
import com.mascara.electronicstoremanage.entities.Product;
import com.mascara.electronicstoremanage.enums.discount.DiscountStatus;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.discount.DiscountCreateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountPagingRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountUpdateRequest;
import com.mascara.electronicstoremanage.view_model.discount.DiscountViewModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    private boolean isOverlapUsingLocalDateAndDuration(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        long overlap = Math.min(end1.toEpochDay(), end2.toEpochDay()) -
                Math.max(start1.toEpochDay(), start2.toEpochDay());
        return overlap >= 0;
    }

    @Override
    public Long insert(DiscountCreateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Discount discount = Discount.builder()
                .capaignName(request.getCapaignName())
                .typeDiscount(request.getTypeDiscount())
                .description(request.getDescription())
                .dateStart(request.getDateStart())
                .dateEnd(request.getDateEnd())
                .discountValue(request.getDiscountValue())
                .status(request.getStatus())
                .build();
        Long discountId = -1L;
        try {
            tx = session.beginTransaction();
            boolean discountRequestValid = checkListProductCanApplyRangeDate(request.getProductIds(),
                    request.getDateStart(),
                    request.getDateEnd(), null);
            if (request.getStatus().equals(DiscountStatus.NOT_APPLY) || discountRequestValid) {
                List<Product> products = session.createQuery("select p from Product p where p.id in :idProducts and p.deleted is false ", Product.class)
                        .setParameter("idProducts", request.getProductIds())
                        .getResultList();
                discount.setProductSet(products.stream().collect(Collectors.toSet()));
                session.persist(discount);
                discountId = discount.getId();
                tx.commit();
            }
        } catch (
                Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return discountId;
    }

    @Override
    public boolean update(DiscountUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Discount discount = session.find(Discount.class, request.getId());
            boolean discountRequestValid = checkListProductCanApplyRangeDate(request.getProductIds(),
                    request.getDateStart(),
                    request.getDateEnd(), request.getId());
            if (request.getStatus().equals(DiscountStatus.NOT_APPLY) || discountRequestValid) {
                discount.setCapaignName(request.getCapaignName());
                discount.setTypeDiscount(request.getTypeDiscount());
                discount.setDescription(request.getDescription());
                discount.setDateStart(request.getDateStart());
                discount.setDateEnd(request.getDateEnd());
                discount.setDiscountValue(request.getDiscountValue());
                discount.setStatus(request.getStatus());
                discount.setTypeDiscount(request.getTypeDiscount());

                Set<Product> productSet = new LinkedHashSet<>();
                for (Long idProduct : request.getProductIds()) {
                    Product product = session.find(Product.class, idProduct);
                    productSet.add(product);
                }
                discount.setProductSet(productSet);
                tx.commit();
                return HibernateUtils.merge(discount);
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Long idEntity) {
        Session session = HibernateUtils.getSession();
        Discount discount = session.find(Discount.class, idEntity);
        discount.setDeleted(true);
        session.close();
        return HibernateUtils.merge(discount);
    }

    @Override
    public DiscountViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Discount discount = session.find(Discount.class, entityId);
        DiscountViewModel discountViewModel = DiscountMapper.getInstance.entityToViewModel(discount);
        return discountViewModel;
    }

    @Override
    public List<DiscountViewModel> retrieveAll(DiscountPagingRequest request) {
        List<DiscountViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Discount", request);
        Query query = session.createQuery(cmd, Customer.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Discount> discountList = query.getResultList();

        for (Discount discount : discountList) {
            list.add(DiscountMapper.getInstance.entityToViewModel(discount));
        }
        session.close();
        return list;
    }

    @Override
    public Optional<Discount> getDiscountCurrentByProductId(Long productId) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Optional<Discount> discount = session.createQuery("select d from Discount d join d.productSet ps where d.dateStart <= CURRENT_DATE and d.dateEnd >= CURRENT_DATE and d.deleted is false and ps.id =: id", Discount.class)
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

    @Override
    public boolean checkListProductCanApplyRangeDate(List<Long> productIds, LocalDate dateStart, LocalDate dateEnd, Long idDiscountUpdate) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        boolean isValid = true;
        try {
            tx = session.beginTransaction();
            List<Discount> discounts;
            if (productIds != null && productIds.size() > 0) {
//                Create discount
                if (idDiscountUpdate == null) {
                    discounts =
                            session.createQuery("select distinct d from Discount d join d.productSet ps where ps.id in :idProducts", Discount.class)
                                    .setParameter("idProducts", productIds)
                                    .getResultList();
                } else {
//                    Update discount ( so idDiscountUpdate != null)
                    discounts = session.createQuery("select distinct d from Discount d join d.productSet ps where ps.id in :idProducts and d.id !=: idDiscount", Discount.class)
                            .setParameter("idProducts", productIds)
                            .setParameter("idDiscount", idDiscountUpdate)
                            .getResultList();
                }
                if (discounts.size() > 0) {
                    boolean overLapTime = false;
                    for (Discount itemDiscount : discounts) {
                        if (isOverlapUsingLocalDateAndDuration(dateStart, dateEnd,
                                itemDiscount.getDateStart(),
                                itemDiscount.getDateEnd())) {
                            overLapTime = true;
                            break;
                        }
                    }
                    if (overLapTime)
                        isValid = false;
                }
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return isValid;
    }
}
