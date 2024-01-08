package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class OrderItem {
    private Product product;
    private Integer quantity;
    private BigDecimal price;
    private String notes;//Notes or comments for the product I order, i.e. extra sauce or without sauce
}
