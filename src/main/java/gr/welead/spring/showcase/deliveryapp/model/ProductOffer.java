package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class ProductOffer extends BaseModel {//product that has offer
    private Offer offer;
    private Product product;
}
