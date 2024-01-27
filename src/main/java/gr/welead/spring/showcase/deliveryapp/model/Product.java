package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "PRODUCTS", indexes = {@Index(columnList = "serial")})
@SequenceGenerator(name = "idGenerator", sequenceName = "PRODUCTS_SEQ", initialValue = 1, allocationSize = 1)
public class Product extends BaseModel {
    @NotNull
    @Column(length = 50, nullable = false)
    private String name;

    @NotNull
    @Column(length = 30, nullable = false, unique = true)
    private String serial;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductCategory category;

    //The products from the store
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    //The offers
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Offer offer;

    //The products with offers
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductOffer> productOffers = new HashSet<>();
}
