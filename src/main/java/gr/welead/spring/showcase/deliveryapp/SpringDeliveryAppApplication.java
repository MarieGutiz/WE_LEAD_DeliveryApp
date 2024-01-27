package gr.welead.spring.showcase.deliveryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class SpringDeliveryAppApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringDeliveryAppApplication.class, args);
        SpringApplication application = new SpringApplication(SpringDeliveryAppApplication.class);
        application.setDefaultProperties(Collections.singletonMap("spring.profiles.active", "stores, coupons, customers, orders"));
        application.run(args);
    }

}
