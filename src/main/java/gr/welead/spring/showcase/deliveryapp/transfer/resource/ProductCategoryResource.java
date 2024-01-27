package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ProductCategoryResource extends BaseResource {
    @NotBlank(message = "Description must not be blank")
    private String description;
}
