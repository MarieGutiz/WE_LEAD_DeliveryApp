package gr.welead.spring.showcase.deliveryapp.transfer;

import gr.welead.spring.showcase.deliveryapp.model.OrderStatus;
import gr.welead.spring.showcase.deliveryapp.model.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderDetailsReport {
    Long getOrder_id();

    LocalDateTime getOrderDate();

    BigDecimal getSubTotal();

    BigDecimal getTotal();

    OrderStatus getOrderStatus();

    PaymentMethod getPaymentMethod();

    Long getCustomer_id();

    String getCustomer_firstName();

    String getCustomer_lastName();

    Long getStore_id();

    String getStore_name();

    Long getOrderItem_id();

    BigDecimal getOrderItem_price();

    Integer getOrderItem_quantity();

    Long getProduct_id();

    String getProduct_name();
}
