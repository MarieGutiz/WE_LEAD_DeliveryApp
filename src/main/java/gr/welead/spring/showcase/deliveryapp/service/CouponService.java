package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Coupon;

import java.time.LocalDateTime;
import java.util.Optional;


public interface CouponService extends BaseService<Coupon, Long> {
    Optional<Coupon> findByNameIgnoreCase(String couponName);

    Optional<Coupon> validateAndApplyCoupon(String couponName, LocalDateTime currentDateTime);

}
