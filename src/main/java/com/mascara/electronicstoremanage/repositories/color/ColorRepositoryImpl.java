package com.mascara.electronicstoremanage.repositories.color;

import com.mascara.electronicstoremanage.common.mapper.ColorMapper;
import com.mascara.electronicstoremanage.entities.Color;
import com.mascara.electronicstoremanage.entities.Product;
import com.mascara.electronicstoremanage.enums.product.ProductStatusEnum;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.color.ColorCreateRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorPagingRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorUpdateRequest;
import com.mascara.electronicstoremanage.view_model.color.ColorViewModel;
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
 * Filename  : ColorRepositoryImpl
 */
public class ColorRepositoryImpl implements ColorRepository {
    private static ColorRepositoryImpl instance = null;

    public static ColorRepositoryImpl getInstance() {
        if (instance == null)
            instance = new ColorRepositoryImpl();
        return instance;
    }

    private ColorRepositoryImpl() {

    }

    @Override
    public Long insert(ColorCreateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Color color = Color.builder()
                .colorName(request.getColorName())
                .build();
        Long colorId = -1L;
        try {
            tx = session.beginTransaction();
            Optional<Color> colorOptional = session.createQuery("select c from Color c where c.colorName =: name", Color.class)
                    .setParameter("name", request.getColorName()).uniqueResultOptional();
            if (!colorOptional.isPresent()) {
                session.persist(color);
                colorId = color.getId();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return colorId;
    }

    @Override
    public boolean update(ColorUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        try {
            Optional<Color> colorOptional = session.createQuery("select c from Color c where c.colorName =: name and id != :id", Color.class)
                    .setParameter("name", request.getColorName())
                    .setParameter("id", request.getId())
                    .uniqueResultOptional();
            if (!colorOptional.isPresent()) {
                Color color = session.find(Color.class, request.getId());
                color.setColorName(request.getColorName());
                session.persist(color);
                return HibernateUtils.merge(color);
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
        Color color = session.find(Color.class, idEntity);
        Query query = session.createQuery("select p.id from Product p join p.colorSet cs where cs =: s1 and p.deleted is false", Product.class);
        query.setParameter("s1", color.getId());
        List<Long> productIds = query.list();

        for (Long id : productIds) {
            Product subProduct = session.find(Product.class, id);
            if (!subProduct.getStatus().equals(ProductStatusEnum.STOP_BUSINESS))
                return false;
        }
        color.setDeleted(true);
        session.close();
        return HibernateUtils.merge(color);
    }

    @Override
    public ColorViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Color color = session.find(Color.class, entityId);
        ColorViewModel colorViewModel = ColorMapper.getInstance.entityToViewModel(color);
        return colorViewModel;
    }

    @Override
    public List<ColorViewModel> retrieveAll(ColorPagingRequest request) {
        List<ColorViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Color", request);
        Query query = session.createQuery(cmd, Color.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Color> colorList = query.list();

        for (Color color : colorList) {
            list.add(ColorMapper.getInstance.entityToViewModel(color));
        }
        session.close();
        return list;
    }
}
