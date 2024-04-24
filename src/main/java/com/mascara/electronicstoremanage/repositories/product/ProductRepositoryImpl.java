package com.mascara.electronicstoremanage.repositories.product;

import com.mascara.electronicstoremanage.common.mapper.ProductMapper;
import com.mascara.electronicstoremanage.entities.*;
import com.mascara.electronicstoremanage.utils.HibernateUtils;
import com.mascara.electronicstoremanage.view_model.discount.ProductApplyPagingRequest;
import com.mascara.electronicstoremanage.view_model.discount.ProductApplyViewModel;
import com.mascara.electronicstoremanage.view_model.product.ProductCreateRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductPagingRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductUpdateRequest;
import com.mascara.electronicstoremanage.view_model.product.ProductViewModel;
import com.mascara.electronicstoremanage.view_model.sale.ProductSalePagingRequest;
import com.mascara.electronicstoremanage.view_model.sale.ProductSaleViewModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 15/04/2024
 * Time      : 6:24 CH
 * Filename  : ProductRepositoryImpl
 */
public class ProductRepositoryImpl implements ProductRepository {
    private static ProductRepositoryImpl instance = null;

    public static ProductRepositoryImpl getInstance() {
        if (instance == null)
            instance = new ProductRepositoryImpl();
        return instance;
    }

    private ProductRepositoryImpl() {

    }

    @Override
    public Long insert(ProductCreateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction tx = null;
        UUID uuidCode = UUID.randomUUID();
        Product product = Product.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .pathImage(request.getPathImage())
                .importPrice(request.getImportPrice())
                .salePrice(request.getSalePrice())
                .quantity(request.getQuantity())
                .origin(request.getOrigin())
                .weight(request.getWeight())
                .weightUnit(request.getWeightUnit())
                .warrantyPeriod(request.getWarrantyPeriod())
                .warrantyPeriodUnit(request.getWarrantyPeriodUnit())
                .size(request.getSize())
                .code(uuidCode.toString())
                .status(request.getStatus())
                .build();
        Long productId = -1L;
        try {
            tx = session.beginTransaction();
            Optional<Brand> brandOptional = session.createQuery("select b from Brand b where b.brandName =: brandName", Brand.class)
                    .setParameter("brandName", request.getBrandName()).uniqueResultOptional();
            Optional<Material> materialOptional = session.createQuery("select m from Material m where m.materialName =: materialName", Material.class)
                    .setParameter("materialName", request.getMaterialName()).uniqueResultOptional();
            Optional<Category> categoryOptional = session.createQuery("select c from Category c where c.categoryName =: categoryName", Category.class)
                    .setParameter("categoryName", request.getCategoryName()).uniqueResultOptional();
            List<Feature> featureList = session.createQuery("select f from Feature f where f.featureName in (:featureNameList)", Feature.class)
                    .setParameter("featureNameList", request.getFeatureNameList()).getResultList();
            List<Color> colorList = session.createQuery("select c from Color c where c.colorName in (:colorNameList)", Color.class)
                    .setParameter("colorNameList", request.getColorNameList()).getResultList();

            if (brandOptional.isPresent() &&
                    materialOptional.isPresent() &&
                    categoryOptional.isPresent() &&
                    featureList.size() > 0 &&
                    colorList.size() > 0) {
                product.setBrand(brandOptional.get());
                product.setBrandId(brandOptional.get().getId());
                product.setMaterial(materialOptional.get());
                product.setMaterialId(materialOptional.get().getId());
                product.setCategory(categoryOptional.get());
                product.setCategoryId(categoryOptional.get().getId());
                product.setFeatureSet(featureList.stream().collect(Collectors.toSet()));
                product.setColorSet(colorList.stream().collect(Collectors.toSet()));

                session.persist(product);
                productId = product.getId();
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productId;
    }

    @Override
    public boolean update(ProductUpdateRequest request) {
        Session session = HibernateUtils.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Product product = session.find(Product.class, request.getId());
            product.setProductName(request.getProductName());
            product.setDescription(request.getDescription());
            product.setPathImage(request.getPathImage());
            product.setSalePrice(request.getSalePrice());
            product.setImportPrice(request.getImportPrice());
            product.setQuantity(request.getQuantity());
            product.setOrigin(request.getOrigin());
            product.setWeight(request.getWeight());
            product.setWeightUnit(request.getWeightUnit());
            product.setWarrantyPeriod(request.getWarrantyPeriod());
            product.setWarrantyPeriodUnit(request.getWarrantyPeriodUnit());
            product.setSize(request.getSize());
            product.setStatus(request.getStatus());

            Optional<Brand> brandOptional = session.createQuery("select b from Brand b where b.brandName =: brandName", Brand.class)
                    .setParameter("brandName", request.getBrandName()).uniqueResultOptional();
            Optional<Material> materialOptional = session.createQuery("select m from Material m where m.materialName =: materialName", Material.class)
                    .setParameter("materialName", request.getMaterialName()).uniqueResultOptional();
            Optional<Category> categoryOptional = session.createQuery("select c from Category c where c.categoryName =: categoryName", Category.class)
                    .setParameter("categoryName", request.getCategoryName()).uniqueResultOptional();
            List<Feature> featureList = session.createQuery("select f from Feature f where f.featureName in (:featureNameList)", Feature.class)
                    .setParameter("featureNameList", request.getFeatureNameList()).getResultList();
            List<Color> colorList = session.createQuery("select c from Color c where c.colorName in (:colorNameList)", Color.class)
                    .setParameter("colorNameList", request.getColorNameList()).getResultList();

            if (brandOptional.isPresent() &&
                    materialOptional.isPresent() &&
                    categoryOptional.isPresent() &&
                    featureList.size() > 0 &&
                    colorList.size() > 0) {
                product.setBrand(brandOptional.get());
                product.setBrandId(brandOptional.get().getId());
                product.setMaterial(materialOptional.get());
                product.setMaterialId(materialOptional.get().getId());
                product.setCategory(categoryOptional.get());
                product.setCategoryId(categoryOptional.get().getId());
                product.setFeatureSet(featureList.stream().collect(Collectors.toSet()));
                product.setColorSet(colorList.stream().collect(Collectors.toSet()));
                transaction.commit();
                return HibernateUtils.merge(product);
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
        Product product = session.find(Product.class, idEntity);
        Optional<Order> orderOptional = session.createQuery("select o from Order o join OrderItem oi on o.id = oi.orderId where oi.productId =: productId and o.status = 'PENDING'", Order.class)
                .setParameter("productId", idEntity)
                .setMaxResults(1)
                .uniqueResultOptional();
        if (orderOptional.isPresent())
            return false;

        product.setDeleted(true);
        session.close();
        return HibernateUtils.merge(product);
    }

    @Override
    public ProductViewModel retrieveById(Long entityId) {
        Session session = HibernateUtils.getSession();
        Product product = session.find(Product.class, entityId);
        ProductViewModel productViewModel = ProductMapper.getInstance.entityToViewModel(product);
        return productViewModel;
    }

    @Override
    public List<ProductViewModel> retrieveAll(ProductPagingRequest request) {
        List<ProductViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Product", request);
        Query query = session.createQuery(cmd, Product.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Product> productList = query.getResultList();

        for (Product product : productList) {
            list.add(ProductMapper.getInstance.entityToViewModel(product));
        }
        session.close();
        return list;
    }

    @Override
    public List<ProductSaleViewModel> retrieveAllProductSale(ProductSalePagingRequest request) {
        List<ProductSaleViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Product", request);
        Query query = session.createQuery(cmd, Product.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Product> productList = query.getResultList();

        for (Product product : productList) {
            list.add(ProductMapper.getInstance.entityToSaleViewModel(product));
        }
        session.close();
        return list;
    }

    @Override
    public List<ProductApplyViewModel> retrieveAllProductApply(ProductApplyPagingRequest request) {
        List<ProductApplyViewModel> list = new ArrayList<>();
        Session session = HibernateUtils.getSession();
        int offset = (request.getPageIndex() - 1) * request.getPageSize();
        String cmd = HibernateUtils.getRetrieveAllQuery("Product", request);
        Query query = session.createQuery(cmd, Product.class);
        query.setFirstResult(offset);
        query.setMaxResults(request.getPageSize());
        List<Product> productList = query.getResultList();

        for (Product product : productList) {
            list.add(ProductMapper.getInstance.entityToApplyViewModel(product));
        }
        session.close();
        return list;
    }
}
