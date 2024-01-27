package gr.welead.spring.showcase.deliveryapp.bootstrap;

import gr.welead.spring.showcase.deliveryapp.base.BaseComponent;
import gr.welead.spring.showcase.deliveryapp.model.*;
import gr.welead.spring.showcase.deliveryapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("generate-stores-for-app")
@RequiredArgsConstructor
public class StoreSampleContentCreator extends BaseComponent implements CommandLineRunner {
 final StoreService storeService;
 final AccountService accountService;
 final AddressService addressService;
 final ProductService productService;
 final ProductCategoryService productCategoryService;
 final ReviewService reviewService;

    @Override
    public void run(String... args) throws Exception {
        // Create sample account
        Account sampleAccount = Account.builder()
                .firstName("Kerasina")
                .lastName("Tsiava")
                .role(Role.STORE_ADMIN)
                .password("sample_password")
                .email("sample@email.com")
                .build();

        // Create and persist the address independently
        Address sampleAddress = Address.builder()
                .city("Larisa")
                .state("Thessaly")
                .phoneNumber("1234567890")
                .street("Afxentioy Grigorioy")
                .floor(0)
                .departmentType("Isogeio")
                .postalCode(12345)
                .build();


        // Set the associations
        //Save the address

        Address address = addressService.create(sampleAddress);
        logger.info("Created address {}", address);

        sampleAccount.setAddress(address);

        // Save the account
        Account savedAccount = accountService.create(sampleAccount);
        logger.info("Created savedAccount{}.", savedAccount);

        Store daVinceStore = Store.builder()
                .name("Da Vinci")
                .afm(123456789)
                .storeDescription("Cafeteria")
                .ranking(4.5)
                .status(StoreStatus.isOpen)
                .category(StoreCategory.COFFEE_AND_BEVERAGES)
                .time(AverageTime.builder().averageTime("00:12:00").build())  // Sample time
                .build();
        daVinceStore.setAccount(savedAccount);
        daVinceStore.setAddress(address);
        Store savedStore = storeService.create(daVinceStore);
        logger.info("Created store: {}", savedStore);

        List<StoreReview> reviews = reviewService.createAll(
                StoreReview.builder().comment("Nice coffee").rating(4.0).store(savedStore).build(),
                StoreReview.builder().comment("Owner was gentle").rating(5.0).store(savedStore).build(),
                StoreReview.builder().comment("Nice place for children too.").rating(4.5).store(savedStore).build()
        );

        // Create sample products
        ProductCategory coffeeCategory = productCategoryService.create(ProductCategory.builder().description("Coffee").build());
        logger.info("Created coffeeCategory{}.", coffeeCategory);

        Product coffee1 = Product.builder()
                .name("Cappuccino")
                .serial("CAP-001")
                .price(BigDecimal.valueOf(2.50))
                .category(coffeeCategory)
                .store(savedStore)
                .build();

        Product coffee2 = Product.builder()
                .name("Latte")
                .serial("LAT-001")
                .price(BigDecimal.valueOf(3.00))
                .category(coffeeCategory)
                .store(savedStore)
                .build();

        Product coffee3 = Product.builder()
                .name("Espresso")
                .serial("ESP-001")
                .price(BigDecimal.valueOf(2.00))
                .category(coffeeCategory)
                .store(savedStore)
                .build();

        // Save the products
        List<Product> savedProducts = productService.createAll(coffee1, coffee2, coffee3);
        logger.info("savedProducts {} ", savedProducts);

    }
}
