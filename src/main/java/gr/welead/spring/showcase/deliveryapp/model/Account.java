package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Account extends BaseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Address address;
    private LocalDateTime registeredDateTime;//The date of the account creation it has to be automatically
}
