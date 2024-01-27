package gr.welead.spring.showcase.deliveryapp.model;

import gr.welead.spring.showcase.deliveryapp.util.CouponNameGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor()
@ToString(callSuper = true)
@Entity
@Table(name = "COUPONS")
@SequenceGenerator(name = "idGenerator", sequenceName = "COUPONS_SEQ", initialValue = 1, allocationSize = 1)
public class Coupon extends BaseModel {//coupons provided by we lead delivery for discount
    @NotBlank(message = "Coupon name must not be blank")
    @NotNull
    @Pattern(regexp = "^WELEAD[a-zA-Z0-9]+$", message = "Invalid coupon name format")
    @Column(nullable = false, unique = true)
    private String name; // the coupon WELEAD5MG874RJS

    @Column(nullable = true)
    private Float discount; // if any
    @Column(nullable = true)
    private BigDecimal fixedAmount;//let's say is 5 euros, if any

    @NotNull(message = "Expiration date cannot be null")
    @Column(nullable = false)
    private LocalDateTime expirationDate;

    @Column(nullable = false)
    private Boolean isUsed;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Account createdBy;

    // Static method to generate a coupon name using the CouponNameGenerator
    public static String generateCouponName() {
        return CouponNameGenerator.generateCouponName();
    }
    // Validation method to ensure either discount or fixedAmount is set, not both
    public boolean isValid() {
        return (discount == null || fixedAmount == null) && (discount != null || fixedAmount != null);
    }
}
