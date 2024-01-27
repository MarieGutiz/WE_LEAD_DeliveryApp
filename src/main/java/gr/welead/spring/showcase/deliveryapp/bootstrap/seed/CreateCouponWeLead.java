package gr.welead.spring.showcase.deliveryapp.bootstrap.seed;

import gr.welead.spring.showcase.deliveryapp.base.BaseComponent;
import gr.welead.spring.showcase.deliveryapp.model.Account;
import gr.welead.spring.showcase.deliveryapp.model.Address;
import gr.welead.spring.showcase.deliveryapp.model.Coupon;
import gr.welead.spring.showcase.deliveryapp.model.Role;
import gr.welead.spring.showcase.deliveryapp.service.AccountService;
import gr.welead.spring.showcase.deliveryapp.service.AddressService;
import gr.welead.spring.showcase.deliveryapp.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Profile("coupons")
@RequiredArgsConstructor
public class CreateCouponWeLead  extends BaseComponent implements CommandLineRunner {
    final CouponService couponService;
    final AccountService accountService;
    final AddressService addressService;
    @Override
    public void run(String... args) throws Exception {
        // Create sample address for master admin
        Address sampleAddress = Address.builder()
                .city("Xania")
                .state("Crete")
                .phoneNumber("1234567890")
                .street("Agios Konstantinos 5")
                .floor(2)
                .departmentType("Polykatoikia")
                .postalCode(67890)
                .build();

        // Persist the address

        Address address = addressService.create(sampleAddress);
        logger.info("Created address {}", address);

        // Create sample account
        Account sampleAccount = Account.builder()
                .firstName("Eirinh")
                .lastName("Tsiantas")
                .role(Role.MASTER_ADMIN)
                .password("eirinh_password")
                .email("etsiantas@email.com")
                .build();

        sampleAccount.setAddress(address);
        //persist account
        Account account = accountService.create(sampleAccount);

        // Create coupon sample
        Coupon coupon = Coupon.builder()
//                .name(Coupon.generateCouponName()) //use the generator to create coupon name
                .name("WELEAD5MG874RJS")
                .discount(null) // Set your desired discount value
                .fixedAmount(BigDecimal.valueOf(5)) // Set your desired fixed amount, 5 euros discount,I am happy!
                .expirationDate(LocalDateTime.now().plusMonths(1)) // Set expiration date (1 month from now)
                .createdBy(account) // Set the account that created the coupon
                .isUsed(false)
                .build();

        // We check first if there's discount or fixed amount but not both at the same time
        Coupon sampleCoupon = null;
        if(coupon.isValid()){
            sampleCoupon= couponService.create(coupon);
        }

        // Log information about the created coupon
        logger.info("Created coupon with fixed amount : {}", sampleCoupon);

        //Create a coupon with discount 50% off
        Coupon coupon1 = Coupon.builder()
//                .name(Coupon.generateCouponName()) //use the generator to create coupon name
                .name("WELEAD5MG567TYH")
                .discount(50F) // Set your desired discount value = 50% off, wow!
                .fixedAmount(null)
                .expirationDate(LocalDateTime.now().plusMonths(1)) // Set expiration date (1 month from now)
                .createdBy(account) // Set the account that created the coupon
                .isUsed(false)
                .build();

        // We check first if there's discount or fixed amount but not both at the same time
        Coupon sampleCoupon1 = null;
        if(coupon1.isValid()){
            //We just persist the coupon
            sampleCoupon1= couponService.create(coupon1);
        }

        // Log information about the created coupon
        logger.info("Created coupon with discount : {}", sampleCoupon1);


    }
}
