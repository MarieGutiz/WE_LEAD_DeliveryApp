package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class ProductCategory extends BaseModel {
    private String description;
}
