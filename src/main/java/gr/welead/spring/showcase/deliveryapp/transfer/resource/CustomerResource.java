package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class CustomerResource extends BaseResource {
    AccountResource accountResource;
    RoyaltyProgramResource royaltyProgram;

}
