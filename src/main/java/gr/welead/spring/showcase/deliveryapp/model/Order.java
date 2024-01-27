package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity
@Table(name = "ORDERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
public class Order extends BaseModel {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime orderDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private final Set<OrderItem> orderItems = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(length = 11, nullable = false)
    private OrderStatus orderStatus;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal subTotal;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Delivery delivery;//Check

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(length = 11, nullable = false)
    private PaymentMethod paymentMethod;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Coupon appliedCoupon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Store store; // from where I bought

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer customer;
}
