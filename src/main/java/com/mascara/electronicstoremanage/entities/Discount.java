package com.mascara.electronicstoremanage.entities;

import com.mascara.electronicstoremanage.entities.base.BasePesistence;
import com.mascara.electronicstoremanage.enums.discount.DiscountStatus;
import com.mascara.electronicstoremanage.enums.discount.TypeDiscountEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:28 CH
 * Filename  : Discount
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discount")
public class Discount extends BasePesistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "capaign_name")
    private String capaignName;

    @Column(name = "type_discount")
    @Enumerated(EnumType.STRING)
    private TypeDiscountEnum typeDiscount;

    @Column(name = "description")
    private String description;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @Column(name = "discount_value")
    private Double discountValue;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DiscountStatus status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "discount_product",
            joinColumns = {@JoinColumn(name = "discount_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
    )
    private Set<Product> productSet;
}
