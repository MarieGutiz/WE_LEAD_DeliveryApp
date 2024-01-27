package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "ORDER_ITEMS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDER_ITEMS_SEQ", initialValue = 1, allocationSize = 1)
public class OrderItem extends BaseModel {
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;

    @ToString.Exclude
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

     //Things to keep in mind:
    // The associated ProductOffer, if any
   // The offer applied to this order item, if any

    private String notes;//Notes or comments for the product I order, i.e. extra sauce or without sauce
}
