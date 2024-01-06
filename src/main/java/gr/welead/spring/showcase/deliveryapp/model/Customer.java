package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Customer extends BaseModel {//The person who orders, only him/her has points
    private Account account;
    private RoyaltyProgram royaltyProgram;
}