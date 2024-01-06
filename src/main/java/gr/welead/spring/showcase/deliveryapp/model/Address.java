package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor()
@ToString(callSuper = true)
public class Address extends BaseModel {
    private String city;
    private String state;
    private long phoneNumber;
    private String street;
    private int floor;
    private String departmentType;
    private int postalCode;
    private String comments;

}