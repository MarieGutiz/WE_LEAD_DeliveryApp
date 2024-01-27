package gr.welead.spring.showcase.deliveryapp.transfer.resource;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ReviewResource extends BaseResource {
    private String comment;
    private Double rating;

}
