package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class RoyaltyProgramResource extends BaseResource {
    @Min(value = 0, message = "Points cannot be negative")
    private long points;

    @NotBlank(message = "Program description is required")
    private String programDescription;
}
