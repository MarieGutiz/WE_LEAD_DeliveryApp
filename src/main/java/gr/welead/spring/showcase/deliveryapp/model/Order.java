package gr.welead.spring.showcase.deliveryapp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class Order extends BaseModel {
    private LocalDateTime orderDate;
    private final Set<OrderItem> orderItems = new HashSet<>();
    private OrderStatus orderStatus;
    private BigDecimal subTotal;
    private Delivery delivery;//Check
    private BigDecimal total;
    private PaymentMethod paymentMethod;
    private Coupon appliedCoupon;
    private Store store; // from what I bought
    private Customer customer;
}