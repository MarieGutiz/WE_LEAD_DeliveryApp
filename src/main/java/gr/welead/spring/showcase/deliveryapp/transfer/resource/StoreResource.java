package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import gr.welead.spring.showcase.deliveryapp.model.StoreCategory;
import gr.welead.spring.showcase.deliveryapp.model.StoreStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
public class StoreResource extends BaseResource {

    @NotBlank(message = "Store name is required")
    private String name;

    @Positive(message = "AFM must be a positive number")//Add more validation to AFM
    private long afm;

    @NotBlank(message = "Store description is required")
    private String storeDescription;

    @DecimalMin(value = "0.0", message = "Ranking must be greater than or equal to 0.0")
    private Double ranking;

    @NotNull(message = "Store status is required")
    private StoreStatus status;

    private Set<ReviewResource> reviews = new HashSet<>();

    @Valid // Ensure validation of embedded Address object
    private AddressResource address;

    @NotNull(message = "Store category is required")
    private StoreCategory category;

    @NotEmpty(message = "Menu must not be empty")
    private Set<ProductResource> menu = new HashSet<>();

    @Valid // Ensure validation of embedded AverageTime object
    private AverageTimeResource time;

    @Valid // Ensure validation of embedded Account object
    private AccountResource account;
}
