package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "CUSTOMERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "CUSTOMERS_SEQ", initialValue = 1, allocationSize = 1)
public class Customer extends BaseModel {//The person who orders, only him/her has points
    @OneToOne(fetch = FetchType.EAGER)//fix
    private Account account;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private RoyaltyProgram royaltyProgram;

    //For store reviews
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<StoreReview> reviews = new HashSet<>();
}
