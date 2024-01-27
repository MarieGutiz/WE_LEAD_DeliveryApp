package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor()
@Entity
@Table(name = "REVIEWS")
@SequenceGenerator(name = "idGenerator", sequenceName = "REVIEWS_SEQ", initialValue = 1, allocationSize = 1)
public class StoreReview extends BaseModel {
    private String comment;
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Customer customer;
}