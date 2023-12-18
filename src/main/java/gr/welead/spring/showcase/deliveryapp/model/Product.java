package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
    private String name;
    private String serial;//for supermarket
    private BigDecimal price;
    private ProductCategory category;

}
