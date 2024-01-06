package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor()
@ToString(callSuper = true)
public class Coupon extends BaseModel {//coupons provided by we lead delivery for discount
    private String name; // the coupon WELEAD5MG874RJS
    private Float discount; // if any
    private BigDecimal fixedAmount;//let's say is 5 euros, if any
    private LocalDateTime expirationDate;
    private Account createdBy;
    private Boolean isUsed;

    // Static method to generate a coupon name using the CouponNameGenerator
//    public static String generateCouponName() {
//        return CouponNameGenerator.generateCouponName();
//    }
    // Validation method to ensure either discount or fixedAmount is set, not both
    public boolean isValid() {
        return (discount == null || fixedAmount == null) && (discount != null || fixedAmount != null);
    }
}