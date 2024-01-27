package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByNameIgnoreCase(String couponName);

    @Query("SELECT c FROM Coupon c WHERE c.name = :couponName AND c.isUsed = false AND c.expirationDate > :currentDateTime")
    Optional<Coupon> validateAndApplyCoupon(@Param("couponName") String couponName, @Param("currentDateTime") LocalDateTime currentDateTime);

}
