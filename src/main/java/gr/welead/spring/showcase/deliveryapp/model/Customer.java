package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Customer extends Account {//The person who orders, only him/her has points
    private RoyaltyProgram royaltyProgram;
}
