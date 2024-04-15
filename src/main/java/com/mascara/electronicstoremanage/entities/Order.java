package com.mascara.electronicstoremanage.entities;

import com.mascara.electronicstoremanage.entities.base.BasePesistence;
import com.mascara.electronicstoremanage.enums.order.ModeOfDeliveryEnum;
import com.mascara.electronicstoremanage.enums.order.ModeOfPaymentEnum;
import com.mascara.electronicstoremanage.enums.order.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

/**
 * Created by: IntelliJ IDEA
 * User      : boyng
 * Date      : 06/04/2024
 * Time      : 10:28 CH
 * Filename  : Order
 */
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BasePesistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "total_bill")
    private Double totalBill;

    @Column(name = "total_pay")
    private Double totalPay;

    @Column(name = "change_money")
    private Double changeMoney;

    @Column(name = "mode_of_delivery")
    @Enumerated(EnumType.STRING)
    private ModeOfDeliveryEnum modeOfDelivery;

    @Column(name = "mode_of_payment")
    @Enumerated(EnumType.STRING)
    private ModeOfPaymentEnum modeOfPayment;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    @Fetch(FetchMode.SUBSELECT)
    private Set<OrderItem> orderItemSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "staff_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_association_order_staff"),
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Staff staff;

    @Column(name = "staff_id", nullable = false)
    private Long staffId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_association_order_customer"),
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Customer customer;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;
}
