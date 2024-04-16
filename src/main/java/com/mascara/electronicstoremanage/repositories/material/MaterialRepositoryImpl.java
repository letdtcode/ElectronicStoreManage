package com.mascara.electronicstoremanage.repositories.material;

import com.mascara.electronicstoremanage.common.mapper.MaterialMapper;
import com.mascara.electronicstoremanage.entities.Material;
import com.mascara.electronicstoremanage.entities.Product;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.material.MaterialCreateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialPagingRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialUpdateRequest;
import com.mascara.electronicstoremanage.view_model.material.MaterialViewModel;
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
 * Filename  : MaterialRepositoryImpl
 */
public class MaterialRepositoryImpl implements MaterialRepository {
    private static MaterialRepositoryImpl instance = null;

    public static MaterialRepositoryImpl getInstance() {
        if (instance == null)
            instance = new MaterialRepositoryImpl();
        return instance;
    }

    private MaterialRepositoryImpl() {

    }

    @Override
    public Long insert(MaterialCreateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Material material = Material.builder()
                .materialName(request.getMaterialName())
                .build();
        Long materialId = -1L;
        try {
            tx = session.beginTransaction();
            Optional<Material> materialOptional = session.createQuery("select m from Material m where m.materialName =: name", Material.class)
                    .setParameter("name", request.getMaterialName()).uniqueResultOptional();
            if (!materialOptional.isPresent()) {
                session.persist(material);
                materialId = material.getId();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return materialId;
    }

    @Override
    public boolean update(MaterialUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        try {
            Optional<Material> materialOptional = session.createQuery("select m from Material m where m.materialName =: name and id != :id", Material.class)
                    .setParameter("name", request.getMaterialName())
                    .setParameter("id", request.getId())
                    .uniqueResultOptional();
            if (!materialOptional.isPresent()) {
                Material material = session.find(Material.class, request.getId());
                material.setMaterialName(request.getMaterialName());
                return HibernateUtils.merge(material);
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
        Material material = session.find(Material.class, idEntity);

        Query query = session.createQuery("select id from Product where materialId =: s1 and deleted is false", Product.class);
        query.setParameter("s1", material.getId());
        List<Long> productIds = query.getResultList();
        if (productIds.size() > 0)
            return false;

        material.setDeleted(true);
        session.close();
        return HibernateUtils.merge(material);
    }

    @Override
    public MaterialViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Material material = session.find(Material.class, entityId);
        MaterialViewModel materialViewModel = MaterialMapper.getInstance.entityToViewModel(material);
        return materialViewModel;
    }

    @Override
    public List<MaterialViewModel> retrieveAll(MaterialPagingRequest request) {
        List<MaterialViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Material", request);
        Query query = session.createQuery(cmd, Material.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Material> materialList = query.getResultList();

        for (Material material : materialList) {
            list.add(MaterialMapper.getInstance.entityToViewModel(material));
        }
        session.close();
        return list;
    }
}
