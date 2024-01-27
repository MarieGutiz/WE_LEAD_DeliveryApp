package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import gr.welead.spring.showcase.deliveryapp.model.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class AccountResource extends BaseResource {

    @NotNull(message = "Firstname must not be null")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    private String lastName;

    @NotNull(message = "Email must not be null")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Size(min = 6, max = 20, message = "Your password should be between 6 and 10 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?!.*\\s).*$",
            message = "Your password should contain at least one digit, one letter, and one special character."
    )
    private String password;

    @NotNull(message = "Role must not be null")
    private Role role;

    @NotNull(message = "Address must not be null")
    @Valid
    private AddressResource address;
}
