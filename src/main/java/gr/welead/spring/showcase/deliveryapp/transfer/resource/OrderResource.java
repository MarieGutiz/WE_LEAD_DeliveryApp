package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import gr.welead.spring.showcase.deliveryapp.model.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
public class OrderResource extends BaseResource {
    @NotNull(message = "Order Date cannot be null")
    private LocalDateTime orderDate;

    @NotEmpty(message = "Order Items cannot be empty")
    private final Set<OrderItemResource> orderItems = new HashSet<>();

    @NotNull(message = "Order status cannot be null")
    private OrderStatus orderStatus;

    @DecimalMin(value = "0.0", inclusive = false, message = "Subtotal must be greater than 0.0")
    private BigDecimal subTotal;

    @Valid
    private Delivery delivery;//Check

    @DecimalMin(value = "0.0", inclusive = false, message = "Total must be greater than 0.0")
    private BigDecimal total;
    private PaymentMethod paymentMethod;
    private Coupon appliedCoupon;

    @NotNull(message = "Store cannot be null")
    private Store store; // from what I bought

    @NotNull(message = "Customer cannot be null")
    private Customer customer;//me
}
