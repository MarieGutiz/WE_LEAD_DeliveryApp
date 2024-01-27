package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Coupon;
import gr.welead.spring.showcase.deliveryapp.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl extends BaseServiceImpl<Coupon> implements CouponService{
    final CouponRepository couponRepository;
    @Override
    protected JpaRepository<Coupon, Long> getRepository() {
        return couponRepository;
    }


    @Override
    public Optional<Coupon> findByNameIgnoreCase(String couponName) {
        return couponRepository.findByNameIgnoreCase(couponName);
    }

    @Override
    public Optional<Coupon> validateAndApplyCoupon(String couponName, LocalDateTime currentDateTime) {
        return couponRepository.validateAndApplyCoupon(couponName, LocalDateTime.now());
    }
}
