package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
public class RoyaltyProgram extends BaseModel {//For clients
    private long points;
    private String programDescription;
    private Customer customerAccount;
}
