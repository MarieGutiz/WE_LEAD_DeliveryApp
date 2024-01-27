package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
public class CouponResource extends BaseResource{
    // Coupon name should start with "WELEAD" followed by alphanumeric characters.
    @NotBlank(message = "Coupon name must not be blank")
    @Pattern(regexp = "^WELEAD[a-zA-Z0-9]+$", message = "Invalid coupon name format")
    private String name;

    @Positive(message = "Discount should be a positive number")
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount must be greater than 0.0")
    @DecimalMax(value = "100.0", message = "Discount cannot be greater than 100.0")
    private float discount;//if any
    @Positive(message = "Fixed amount should be a positive number")
    private BigDecimal fixedAmount;//let's say is 5 euros, if any

    @Future(message = "Expiration Date should be in the future")
    private LocalDateTime expirationDate;

    @NotNull
    private AccountResource createdBy;

    @NotNull
    private Boolean isUsed;
}
