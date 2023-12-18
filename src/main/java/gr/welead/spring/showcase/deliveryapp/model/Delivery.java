package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString(callSuper = true)
public class Delivery extends BaseModel {
    private BigDecimal baseCost;
    private BigDecimal additionTip;
    private AverageTime time;//time to delivery
}
