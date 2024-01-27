package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor()
@ToString(callSuper = true)
@Entity
@Table(name = "STORES")
@SequenceGenerator(name = "idGenerator", sequenceName = "STORES_SEQ", initialValue = 1, allocationSize = 1)
public class Store extends BaseModel {
    @NotNull
    private String name; // Store name

    @NotNull
    @Column(length = 10, nullable = false)
    private long afm;

    @NotNull
    private String storeDescription;
    private Double ranking;

    @Enumerated(EnumType.STRING)
    @Column(length = 8, nullable = false)
    private StoreStatus status; // Are we open or close?

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<StoreReview> reviews = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private StoreCategory category;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Product> menu = new HashSet<>(); // The store sells?

    @Embedded
    private AverageTime time; // Check class

    @OneToOne(fetch = FetchType.EAGER)
    private Account account; // The owner of the store and his/her details

    // The offers provided by the store
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Offer> offers = new HashSet<>();
}
