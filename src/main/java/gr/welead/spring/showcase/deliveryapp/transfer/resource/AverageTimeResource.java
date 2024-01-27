package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class AverageTimeResource extends BaseResource{
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$", message = "Invalid time format (HH:mm:ss)")
    private String averageTime;
}
