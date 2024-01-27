package gr.welead.spring.showcase.deliveryapp.transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProductOfferDetails {
    BigDecimal getPrice();
    String getProduct_category_description();
    Long getProduct_id();
    String getSerial();
    String getName();
    String getStore_name();
    Integer getBuyquantity();
    BigDecimal getDiscount();
    Integer getFreequantity();
    LocalDateTime getEnddate();
    Long getOffer_id();
    LocalDateTime getStartdate();
    String getOffertype();
    String getDescription();
    String getOffer_name();
}
