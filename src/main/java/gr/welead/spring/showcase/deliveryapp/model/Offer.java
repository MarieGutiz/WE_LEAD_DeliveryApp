package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OFFERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "OFFERS_SEQ", initialValue = 1, allocationSize = 1)
public class Offer extends BaseModel {//or set from predefined offers
    @NotBlank(message = "Name cannot be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 1, max = 255, message = "Description length must be between 1 and 255 characters")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Start date cannot be null")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime startDate;

    @NotNull(message = "Start date cannot be null")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 250, nullable = false)
    private OfferType offerType;  // Enum indicating the type of offer (e.g., BOGO)

    private int buyQuantity;  // The quantity to buy to get the offer
    private int freeQuantity;  // The quantity offered for free
    private Float discount;  // Percentage discount

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Store store;//owner of the offer

    // The products with offers
    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProductOffer> products = new ArrayList<>();
}
