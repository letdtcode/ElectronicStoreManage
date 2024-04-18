package com.mascara.electronicstoremanage.repositories.category;

import com.mascara.electronicstoremanage.common.mapper.CategoryMapper;
import com.mascara.electronicstoremanage.entities.Category;
import com.mascara.electronicstoremanage.entities.Product;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.category.CategoryCreateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryPagingRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryUpdateRequest;
import com.mascara.electronicstoremanage.view_model.category.CategoryViewModel;
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
 * Filename  : CategoryRepositoryImpl
 */
public class CategoryRepositoryImpl implements CategoryRepository {
    private static CategoryRepositoryImpl instance = null;

    public static CategoryRepositoryImpl getInstance() {
        if (instance == null)
            instance = new CategoryRepositoryImpl();
        return instance;
    }

    private CategoryRepositoryImpl() {

    }

    @Override
    public Long insert(CategoryCreateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        Category category = Category.builder()
                .categoryName(request.getCategoryName())
                .build();
        Long categoryId = -1L;
        try {
            tx = session.beginTransaction();
            Optional<Category> categoryOptional = session.createQuery("select c from Category c where c.categoryName =: name", Category.class)
                    .setParameter("name", request.getCategoryName()).uniqueResultOptional();
            if (!categoryOptional.isPresent()) {
                session.persist(category);
                categoryId = category.getId();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categoryId;
    }

    @Override
    public boolean update(CategoryUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        try {
            Optional<Category> categoryOptional = session.createQuery("select c from Category c where c.categoryName =: name and id != :id", Category.class)
                    .setParameter("name", request.getCategoryName())
                    .setParameter("id", request.getId())
                    .uniqueResultOptional();
            if (!categoryOptional.isPresent()) {
                Category category = session.find(Category.class, request.getId());
                category.setCategoryName(request.getCategoryName());
                return HibernateUtils.merge(category);
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
        Category category = session.find(Category.class, idEntity);
        Query query = session.createQuery("select id from Product where categoryId =: s1 and deleted is false", Product.class);
        query.setParameter("s1", category.getId());
        List<Long> productIds = query.getResultList();
        if (productIds.size() > 0)
            return false;

        session.close();
        category.setDeleted(true);
        return HibernateUtils.merge(category);
    }

    @Override
    public CategoryViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Category category = session.find(Category.class, entityId);
        CategoryViewModel categoryViewModel = CategoryMapper.getInstance.entityToViewModel(category);
        return categoryViewModel;
    }

    @Override
    public List<CategoryViewModel> retrieveAll(CategoryPagingRequest request) {
        List<CategoryViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Category", request);
        Query query = session.createQuery(cmd, Category.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Category> categoryList = query.getResultList();

        for (Category category : categoryList) {
            list.add(CategoryMapper.getInstance.entityToViewModel(category));
        }
        session.close();
        return list;
    }
}
