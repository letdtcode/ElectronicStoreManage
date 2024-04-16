package com.mascara.electronicstoremanage.repositories.feature;

import com.mascara.electronicstoremanage.common.mapper.FeatureMapper;
import com.mascara.electronicstoremanage.entities.Feature;
import com.mascara.electronicstoremanage.entities.Product;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.feature.FeatureCreateRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeaturePagingRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureUpdateRequest;
import com.mascara.electronicstoremanage.view_model.feature.FeatureViewModel;
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
 * Time      : 6:23 CH
 * Filename  : FeatureRepositoryImpl
 */
public class FeatureRepositoryImpl implements FeatureRepository {
    private static FeatureRepositoryImpl instance = null;

    public static FeatureRepositoryImpl getInstance() {
        if (instance == null)
            instance = new FeatureRepositoryImpl();
        return instance;
    }

    private FeatureRepositoryImpl() {

    }

    @Override
    public Long insert(FeatureCreateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Feature feature = Feature.builder()
                .featureName(request.getFeatureName())
                .build();
        Long featureId = -1L;
        try {
            tx = session.beginTransaction();
            Optional<Feature> featureOptional = session.createQuery("select f from Feature f where f.featureName =: name", Feature.class)
                    .setParameter("name", request.getFeatureName()).uniqueResultOptional();
            if (!featureOptional.isPresent()) {
                session.persist(feature);
                featureId = feature.getId();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return featureId;
    }

    @Override
    public boolean update(FeatureUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        try {
            Optional<Feature> featureOptional = session.createQuery("select f from Feature f where f.featureName =: name and id != :id", Feature.class)
                    .setParameter("name", request.getFeatureName())
                    .setParameter("id", request.getId())
                    .uniqueResultOptional();
            if (!featureOptional.isPresent()) {
                Feature feature = session.find(Feature.class, request.getId());
                feature.setFeatureName(request.getFeatureName());
                return HibernateUtils.merge(feature);
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
        Feature feature = session.find(Feature.class, idEntity);

        Query query = session.createQuery("select p.id from Product p join p.featureSet fs where fs.id =: s1 and p.deleted is false", Product.class);
        query.setParameter("s1", feature.getId());
        List<Long> productIds = query.getResultList();
        if (productIds.size() > 0)
            return false;

        feature.setDeleted(true);
        session.close();
        return HibernateUtils.merge(feature);
    }

    @Override
    public FeatureViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Feature feature = session.find(Feature.class, entityId);
        FeatureViewModel featureViewModel = FeatureMapper.getInstance.entityToViewModel(feature);
        return featureViewModel;
    }

    @Override
    public List<FeatureViewModel> retrieveAll(FeaturePagingRequest request) {
        List<FeatureViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Feature", request);
        Query query = session.createQuery(cmd, Feature.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Feature> featureList = query.getResultList();

        for (Feature feature : featureList) {
            list.add(FeatureMapper.getInstance.entityToViewModel(feature));
        }
        session.close();
        return list;
    }
}
