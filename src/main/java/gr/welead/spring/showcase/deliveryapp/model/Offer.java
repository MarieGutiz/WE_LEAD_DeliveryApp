package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Offer extends BaseModel {//or set from predefined offers
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private OfferType offerType;  // Enum indicating the type of offer (e.g., BOGO)
    private int buyQuantity;  // The quantity to buy to get the offer
    private int freeQuantity;  // The quantity offered for free
    private float discount;  // Percentage discount (if any)
    private Store store;//owner of the offer
}
