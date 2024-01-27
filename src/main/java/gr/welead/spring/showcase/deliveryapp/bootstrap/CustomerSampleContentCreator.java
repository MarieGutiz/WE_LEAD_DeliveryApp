package gr.welead.spring.showcase.deliveryapp.bootstrap;

import gr.welead.spring.showcase.deliveryapp.base.BaseComponent;
import gr.welead.spring.showcase.deliveryapp.model.*;
import gr.welead.spring.showcase.deliveryapp.service.AccountService;
import gr.welead.spring.showcase.deliveryapp.service.AddressService;
import gr.welead.spring.showcase.deliveryapp.service.CustomerService;
import gr.welead.spring.showcase.deliveryapp.service.RoyaltyProgamService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("generate-customer-for-app")
public class CustomerSampleContentCreator extends BaseComponent implements CommandLineRunner {
    final CustomerService customerService;
    final AccountService accountService;
    final AddressService addressService;
    final RoyaltyProgamService royaltyProgamService;

    @Override
    public void run(String... args) throws Exception {
        // Create sample address
        Address sampleAddress = Address.builder()
                .city("Larisa")
                .state("Thessaly")
                .phoneNumber("1234567890")
                .street("Agios Konstantinos 3")
                .floor(2)
                .departmentType("Polykatoikia")
                .postalCode(34125)
                .build();
        //Save the address

        Address address = addressService.create(sampleAddress);
        logger.info("Created address {}", address);

        // Create sample account
        Account sampleAccount = Account.builder()
                .firstName("Marie")
                .lastName("Smith")
                .role(Role.CUSTOMER)
                .password("marie_password")
                .email("marie@email.com")
                .build();

        // Set the associations
        sampleAccount.setAddress(address);

        // Save the account
        Account savedAccount = accountService.create(sampleAccount);
        logger.info("Created account: {}", savedAccount);

        // Save the royalty program
        RoyaltyProgram royaltyProgram = RoyaltyProgram.builder()
                .points(100L)
                .programDescription(String.valueOf(OfferType.FIXED_AMOUNT_DISCOUNT))
                .build();

        royaltyProgram = royaltyProgamService.create(royaltyProgram); // Save the royalty program

        // save the customer
        Customer customer = Customer.builder()
                .account(savedAccount)
                .royaltyProgram(royaltyProgram)
                .build();

        customerService.create(customer);
        // Log information about the created customer
        logger.info("Created customer: {}", customer);
    }
}
