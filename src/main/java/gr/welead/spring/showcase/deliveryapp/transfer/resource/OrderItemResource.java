package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import gr.welead.spring.showcase.deliveryapp.model.ProductOffer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
public class OrderItemResource extends BaseResource{

    @NotNull(message = "Product is required")
    @Valid // Ensure validation of embedded Product object
    private ProductResource product;

    @Positive(message = "Quantity must be a positive number")
    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @DecimalMin(value = "0.0", message = "Price must be greater than or equal to 0.0")
    @NotNull(message = "Price is required")
    private BigDecimal price;

    @Valid // Ensure validation of embedded ProductOffer object, if any
    private ProductOffer productOffer;

    @Valid // Ensure validation of embedded Offer object, if any
    private OfferResource appliedOffer;

    @Size(max = 255, message = "Notes cannot exceed 255 characters")
    private String notes;
}
