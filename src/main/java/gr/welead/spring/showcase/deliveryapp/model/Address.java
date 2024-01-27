package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor()
@ToString(callSuper = true)
@Entity
@Table(name = "ADDRESSES")
@SequenceGenerator(name = "idGenerator", sequenceName = "ADDRESSES_SEQ", initialValue = 1, allocationSize = 1)
public class Address extends BaseModel {

    @NotNull(message = "City cannot be null")
    @Column(nullable = false)
    private String city;

    private String state;

    @NotNull(message = "Phone Number cannot be null")
    @Column(nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @NotNull(message = "Street cannot be null")
    @Column(nullable = false)
    private String street;

    @NotNull(message = "Floor cannot be null")
    @Column(nullable = false)
    private int floor;

    @NotNull(message = "Department type be null")
    @Column(nullable = false, length = 50)
    private String departmentType;

    @NotNull(message = "Postal code cannot be null")
    @Column(nullable = false, length = 6)
    private int postalCode;

    @Column(nullable = true)
    private String comment;

}
