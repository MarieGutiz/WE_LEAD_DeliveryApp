package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class AddressResource extends BaseResource{
    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @Pattern(regexp = "\\+30-[0-9]{10}", message = "Phone number must start with '+30-' and have 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Street is required")
    private String street;

    @Min(value = 0, message = "Floor number cannot be negative")
    private int floor;

    private String departmentType;

    @Min(value = 0, message = "Postal code cannot be negative")
    private int postalCode;

    private String comments;
}
