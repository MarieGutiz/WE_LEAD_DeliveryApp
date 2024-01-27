package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ProductOfferResource extends BaseResource{
    @NotNull(message = "If there's a product with offer, offer resource must not be null")
    private OfferResource offer;
    @NotNull(message = "Product with offer, must have products, product must not be null")
    private ProductResource product;
}
