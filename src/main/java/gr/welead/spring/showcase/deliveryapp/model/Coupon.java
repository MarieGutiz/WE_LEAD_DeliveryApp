package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends BaseModel {//coupons provided by we lead delivery for discount
    private String name; // the coupon WELEAD5MG874RJS
    private float discount;//if any
    private BigDecimal fixedAmount;//let's say is 5 euros, if any
    private LocalDateTime expirationDate;
}
