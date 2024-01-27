package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor()
@ToString(callSuper = true)
@Entity
@Table(name = "PRODUCT_OFFERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "PRODUCT_OFFERS_SEQ", initialValue = 1, allocationSize = 1)
public class ProductOffer extends BaseModel {
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private Offer offer;
}
