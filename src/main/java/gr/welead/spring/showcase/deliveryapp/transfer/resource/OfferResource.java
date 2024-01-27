package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import gr.welead.spring.showcase.deliveryapp.model.OfferType;
import gr.welead.spring.showcase.deliveryapp.model.Store;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
public class OfferResource extends BaseResource{
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Start date cannot be null")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDateTime endDate;

    @NotNull(message = "Offer type cannot be null")
    private OfferType offerType;

    @Min(value = 1, message = "Buy quantity must be at least 1")
    private int buyQuantity;

    @Min(value = 0, message = "Free quantity cannot be negative")
    private int freeQuantity;

    @Positive(message = "Discount should be positive")
    @DecimalMin(value = "0.0", inclusive = false, message = "Discount must be greater than 0.0")
    @DecimalMax(value = "100.0", message = "Discount cannot be greater than 100.0")
    private float discount;

    @NotNull(message = "Store cannot be null")
    @Valid // Assuming Store class has its own validations
    private Store store;

}
