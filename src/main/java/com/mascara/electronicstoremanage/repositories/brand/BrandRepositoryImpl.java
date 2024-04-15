package com.mascara.electronicstoremanage.repositories.brand;

import com.mascara.electronicstoremanage.common.mapper.BrandMapper;
import com.mascara.electronicstoremanage.entities.Brand;
import com.mascara.electronicstoremanage.entities.Product;
import com.mascara.electronicstoremanage.enums.product.ProductStatusEnum;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.brand.BrandCreateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandPagingRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandUpdateRequest;
import com.mascara.electronicstoremanage.view_model.brand.BrandViewModel;
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
 * Time      : 6:21 CH
 * Filename  : BrandRepositoryImpl
 */
public class BrandRepositoryImpl implements BrandRepository {
    private static BrandRepositoryImpl instance = null;

    public static BrandRepositoryImpl getInstance() {
        if (instance == null)
            instance = new BrandRepositoryImpl();
        return instance;
    }

    private BrandRepositoryImpl() {

    }

    @Override
    public Long insert(BrandCreateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Brand brand = Brand.builder()
                .brandName(request.getBrandName())
                .build();
        Long brandId = -1L;
        try {
            tx = session.beginTransaction();
            Optional<Brand> brandOptional = session.createQuery("select b from Brand b where b.brandName =: name", Brand.class)
                    .setParameter("name", request.getBrandName()).uniqueResultOptional();
            if (!brandOptional.isPresent()) {
                session.persist(brand);
                brandId = brand.getId();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return brandId;
    }

    @Override
    public boolean update(BrandUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        try {
            Optional<Brand> brandOptional = session.createQuery("select b from Brand b where b.brandName =: name and id != :id", Brand.class)
                    .setParameter("name", request.getBrandName())
                    .setParameter("id", request.getId())
                    .uniqueResultOptional();
            if (!brandOptional.isPresent()) {
                Brand brand = session.find(Brand.class, request.getId());
                brand.setBrandName(request.getBrandName());
                session.persist(brand);
                return HibernateUtils.merge(brand);
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
        Brand brand = session.find(Brand.class, idEntity);
        Query query = session.createQuery("select id from Product where brandId =: s1 and deleted is false", Product.class);
        query.setParameter("s1", brand.getId());
        List<Long> productIds = query.list();

        for (Long id : productIds) {
            Product subProduct = session.find(Product.class, id);
            if (!subProduct.getStatus().equals(ProductStatusEnum.STOP_BUSINESS))
                return false;
        }
        brand.setDeleted(true);
        session.close();
        return HibernateUtils.merge(brand);
    }

    @Override
    public BrandViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Brand brand = session.find(Brand.class, entityId);
        BrandViewModel brandViewModel = BrandMapper.getInstance.entityToViewModel(brand);
        return brandViewModel;
    }

    @Override
    public List<BrandViewModel> retrieveAll(BrandPagingRequest request) {
        List<BrandViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Brand", request);
        Query query = session.createQuery(cmd, Brand.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Brand> brandList = query.list();

        for (Brand brand : brandList) {
            list.add(BrandMapper.getInstance.entityToViewModel(brand));
        }
        session.close();
        return list;
    }
}
