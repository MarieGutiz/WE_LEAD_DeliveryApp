package gr.welead.spring.showcase.deliveryapp.bootstrap.seed;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.File;

@Component
@Profile("customers")
@RequiredArgsConstructor
public class CustomerJsonParser extends BaseComponent implements CommandLineRunner {
    private final CustomerService customerService;
    private final AccountService accountService;
    private final AddressService addressService;
    private final RoyaltyProgamService royaltyProgamService;

    @Override
    public void run(String... args) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("target/classes/data/accounts_seed.json");
            JsonNode jsonNode = objectMapper.readTree(file);

            if (jsonNode.has("customers")) {
                JsonNode customersNode = jsonNode.get("customers");
                for (JsonNode customerNode : customersNode) {
                    Customer customer = customerService.create(parseCustomer(customerNode));
                    logger.info("Created customer: {}", customer);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Customer parseCustomer(JsonNode customerNode) {
        Account account = accountService.create(parseAccount(customerNode));
        RoyaltyProgram royaltyProgram = parseRoyaltyProgram(customerNode);

        return Customer.builder()
                .account(account)
                .royaltyProgram(royaltyProgram)
                .build();
    }

    private Account parseAccount(JsonNode customerNode) {
        Address address = addressService.create(parseAddress(customerNode.get("address")));

        return Account.builder()
                .firstName(customerNode.get("firstName").asText())
                .lastName(customerNode.get("lastName").asText())
                .role(Role.valueOf(customerNode.get("role").asText()))
                .password(customerNode.get("password").asText())
                .email(customerNode.get("email").asText())
                .address(address)
                .build();
    }

    private Address parseAddress(JsonNode addressNode) {
        return Address.builder()
                .city(addressNode.get("city").asText())
                .state(addressNode.get("state").asText())
                .phoneNumber(addressNode.get("phoneNumber").asText())
                .street(addressNode.get("street").asText())
                .floor(addressNode.get("floor").asInt())
                .departmentType(addressNode.get("departmentType").asText())
                .postalCode(addressNode.get("postalCode").asInt())
                .build();
    }

    private RoyaltyProgram parseRoyaltyProgram(JsonNode customerNode) {
        JsonNode royaltyProgramNode = customerNode.get("royaltyProgram");

        return RoyaltyProgram.builder()
                .points(royaltyProgramNode.get("points").asLong())
                .programDescription(royaltyProgramNode.get("programDescription").asText())
                .build();
    }
}
