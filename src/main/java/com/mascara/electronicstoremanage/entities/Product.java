package com.mascara.electronicstoremanage.entities;

import com.mascara.electronicstoremanage.entities.base.BasePesistence;
import com.mascara.electronicstoremanage.enums.product.ProductStatusEnum;
import com.mascara.electronicstoremanage.enums.product.WarrantyPeriodUnitENum;
import com.mascara.electronicstoremanage.enums.product.WeightUnitEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 9:50 CH
 * Filename  : Product
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BasePesistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "path_image")
    private String pathImage;

    @Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "import_price")
    private Double importPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "origin")
    private String origin;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "weight_unit")
    @Enumerated(EnumType.STRING)
    private WeightUnitEnum weightUnit;

    @Column(name = "warranty_period")
    private Integer warrantyPeriod;

    @Column(name = "warranty_period_unit")
    @Enumerated(EnumType.STRING)
    private WarrantyPeriodUnitENum warrantyPeriodUnit;

    @Column(name = "size")
    private String size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "brand_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_association_product_brand"),
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Brand brand;

    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "material_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_association_product_material"),
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Material material;

    @Column(name = "material_id", nullable = false)
    private Long materialId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_association_product_category"),
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Category category;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_feature",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "feature_id", referencedColumnName = "id")}
    )
    private Set<Feature> featureSet;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_color",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "color_id", referencedColumnName = "id")}
    )
    private Set<Color> colorSet;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatusEnum status;

    @Column(name = "code")
    private String code;
}
