package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "DELIVERIES")
@SequenceGenerator(name = "idGenerator", sequenceName = "DELIVERIES_SEQ", initialValue = 1, allocationSize = 1)
public class Delivery extends BaseModel {
    @Builder.Default
    private BigDecimal baseCost = BigDecimal.valueOf(0.15);//standard price

    @Column(length = 10, nullable = true)
    private BigDecimal additionTip;

    @Embedded
    private AverageTime time;//time to delivery


}
