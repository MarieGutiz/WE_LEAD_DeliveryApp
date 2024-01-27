package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
public class DeliveryResource extends BaseResource{
    @NotNull(message = "Base cost cannot be null")
    @DecimalMin(value = "0.0", message = "Base cost must not be negative")
    private BigDecimal baseCost;

    @DecimalMin(value = "0.0", message = "Additional tip must not be negative")
    private BigDecimal additionTip;

    @NotNull(message = "Time to delivery cannot be null")
    @Valid // Assuming AverageTime class has its own validations
    private AverageTimeResource time;


}
