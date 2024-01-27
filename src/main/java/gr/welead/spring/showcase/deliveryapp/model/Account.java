package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "ACCOUNTS",indexes = {@Index(columnList = "email")})
@SequenceGenerator(name = "idGenerator", sequenceName = "ACCOUNTS_SEQ", initialValue = 1, allocationSize = 1)
public class Account extends BaseModel {
    @NotNull(message = "First name cannot be null")
    @Column(length = 20, nullable = false)
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Column(length = 20, nullable = false)
    private String lastName;

    @NotNull(message = "Email address cannot be null")
    @Email
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @NotNull(message = "Password is cannot be null")
    @Column(length = 20, nullable = false)
    private String password;

    @NotNull(message = "Password is cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(length = 18, nullable = false)
    private Role role;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime registeredDateTime = LocalDateTime.now();//The date of the account creation it has to be automatically

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    private Address address;
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private Address address;

    //For coupons
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Coupon> createdCoupons = new ArrayList<>();

    //Customers and Royalty Program
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Customer customer;

    //The store
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Store store;

}
