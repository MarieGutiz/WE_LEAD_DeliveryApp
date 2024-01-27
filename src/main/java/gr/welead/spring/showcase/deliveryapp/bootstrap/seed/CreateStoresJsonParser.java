package gr.welead.spring.showcase.deliveryapp.bootstrap.seed;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.welead.spring.showcase.deliveryapp.base.BaseComponent;
import gr.welead.spring.showcase.deliveryapp.model.*;
import gr.welead.spring.showcase.deliveryapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Profile("stores")
@RequiredArgsConstructor
public class CreateStoresJsonParser extends BaseComponent implements CommandLineRunner {
    final AccountService accountService;
    final AddressService addressService;
    final ProductService productService;
    final ProductCategoryService productCategoryService;
    final StoreService storeService;
    final ReviewService reviewService;
    final ProductOfferService productOfferService;
    final OfferService offerService;
    final CustomerService customerService;

    final HashMap<Store, List<Long>> menuMap = new HashMap<>();


    @Override
    public void run(String... args) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File("target/classes/data/stores_seed.json"));
//            logger.info("json node {}", jsonNode);
            if (jsonNode.has("addresses")) {
                parseAddress(jsonNode.get("addresses"));
            }

            if (jsonNode.has("accounts")) {
                parseAccounts(jsonNode.get("accounts"));
            }

            if (jsonNode.has("stores")) {
                parseStores(jsonNode.get("stores"));
            }

            if (jsonNode.has("products")) {
                parseProducts(jsonNode.get("products"));
            }



        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void parseAddress(JsonNode addresses) {
        logger.info("At address {}", addresses);
        if (addresses.isArray()) {
            for (JsonNode addressNode : addresses) {
                String city = addressNode.get("city").asText();
                String state = addressNode.get("state").asText();
                String phoneNumber = addressNode.get("phoneNumber").asText();
                String street = addressNode.get("street").asText();
                int floor = addressNode.get("floor").asInt();
                String departmentType = addressNode.get("departmentType").asText();
                int postalCode = addressNode.get("postalCode").asInt();

                // Create sample address
                Address sampleAddress = Address.builder()
                        .city(city)
                        .state(state)
                        .phoneNumber(phoneNumber)
                        .street(street)
                        .floor(floor)
                        .departmentType(departmentType)
                        .postalCode(postalCode)
                        .build();

                // Save the address or perform any other operations as needed
                Address savedAddress = addressService.create(sampleAddress);
            }
        }
    }
    private void parseAccounts(JsonNode accounts) {
        if(accounts.isArray()){
            // Retrieve all addresses
            List<Address> allAddresses = addressService.findAll();
            AtomicReference<Long> addressId = new AtomicReference<>(2L);
            for (JsonNode accountNode : accounts) {
                String firstName = accountNode.get("firstName").asText();
                String lastName = accountNode.get("lastName").asText();
                String roleStr = accountNode.get("role").asText();
                String password = accountNode.get("password").asText();
                String email = accountNode.get("email").asText();

                // Convert role string to Role enum
                Role role = Role.valueOf(roleStr);

                // Create sample account
                Account sampleAccount = Account.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .role(role)
                        .password(password)
                        .email(email)
                        .build();

                // Find the address with ID ?
                Optional<Address> matchingAddress = allAddresses.stream()
                        .filter(address -> Objects.equals(address.getId(), addressId.getAndSet(addressId.get() + 1))) // Adjust the condition based on your Address entity structure
                        .findFirst();

                // Set the address if found
                matchingAddress.ifPresent(sampleAccount::setAddress);

                // Save the account
                Account savedAccount = accountService.create(sampleAccount);
                logger.info("Created account: {}", savedAccount);
            }
        }
    }

    private void parseStores(JsonNode stores) {

        if (stores.isArray()) {
            for (JsonNode storeNode : stores) {
                String name = storeNode.get("name").asText();
                long afm = storeNode.get("afm").asLong();
                String storeDescription = storeNode.get("storeDescription").asText();
                double ranking = storeNode.get("ranking").asDouble();
                String statusStr = storeNode.get("status").asText();
                JsonNode reviewsNode = storeNode.get("reviews");
                long addressId = storeNode.get("addressId").asLong();
                String categoryStr = storeNode.get("category").asText();
                JsonNode menuNode = storeNode.get("menu");
                String averageTime = storeNode.get("averageTime").asText();
                long accountId = storeNode.get("accountId").asLong();

                // Convert status string to StoreStatus enum
                StoreStatus status = StoreStatus.valueOf(statusStr);

                // Convert category string to StoreCategory enum
                StoreCategory category = StoreCategory.valueOf(categoryStr);

                // Convert menuNode to a list of product IDs
                List<Long> menu = new ArrayList<>();
                if (menuNode.isArray()) {
                    for (JsonNode menuItemNode : menuNode) {
                        menu.add(menuItemNode.asLong());
                    }
                }

                // Retrieve or create the associated address

                Address address = addressService.get(addressId);
                logger.info("address id{}  at {}", addressId, address);

                if (address == null) {
                    logger.error("Address with ID {} not found.", addressId);
                    continue;
                }

                // Retrieve or create the associated account
                Account account = accountService.get(accountId);
                if (account == null) {
                    logger.error("Account with ID {} not found.", accountId);
                    continue;
                }
                // Create sample store
                Store store = Store.builder()
                        .name(name)
                        .afm(afm)
                        .storeDescription(storeDescription)
                        .ranking(ranking)
                        .status(status)
                        .address(address)
                        .category(category)
                        .time(AverageTime.builder().averageTime(averageTime).build())
                        .account(account)
                        .build();

                // Save the store or perform any other operations as needed
                Store savedStore = storeService.create(store);
                logger.info("savedStore {}", savedStore);
                // Convert reviewsNode to a list of StoreReview objects
                List<StoreReview> reviews = new ArrayList<>();
                //The person who wrote the review
//                Customer savedAccount = customerService.get(3L);//This customer has not been created yet!
                logger.info("Creating the reviews {}", reviews);
                if (reviewsNode.isArray()) {
                    for (JsonNode reviewNode : reviewsNode) {
                        String comment = reviewNode.get("comment").asText();
                        double rating = reviewNode.get("rating").asDouble();
                        reviews.add(reviewService.create(StoreReview.builder().comment(comment).rating(rating)
                                .store(savedStore).customer(null)
                                .build()));
                    }
                }
                menuMap.put(savedStore, menu);
                logger.info("Created store: {}", savedStore);
            }
        }

    }

    private void parseProducts(JsonNode products) {
        if (products.isArray()) {
            long productId=1L;

            for (JsonNode productNode : products) {
                String name = productNode.get("name").asText();
                String serial = productNode.get("serial").asText();
                BigDecimal price = new BigDecimal(productNode.get("price").asText());
                String categoryDescription = productNode.get("category").asText();

                // Create or retrieve the product category
                ProductCategory productCategory = productCategoryService.create(ProductCategory.builder()
                        .description(categoryDescription)
                        .build());

                // Create sample product
                Product product = Product.builder()
                        .name(name)
                        .serial(serial)
                        .price(price)
                        .category(productCategory)
                        .build();
                 //Add the store
                for(Store store: menuMap.keySet()){
                    List<Long> items = menuMap.get(store);
                    for(var item: items){
                        if(productId == item){
                            product.setStore(store);
                        }
                    }
                }
                // Save the product or perform any other operations as needed
                Product product1 = productService.create(product);
                productId++;

                logger.info("Created product: {}", product1);
            }

        }

        parseProductOffer(); // create an offer sample
    }

    private void parseProductOffer(){
        Store store = storeService.get(1L);
        Product productSample = productService.get(2L);//Latte
        logger.info("store product offer {}", store);
        Offer offerSample = Offer.builder()
                .name(store.getName() + "offer")
                .offerType(OfferType.BOGO)
                .description(store.getName() + " offer "+ OfferType.BOGO)
                .buyQuantity(1)
                .freeQuantity(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusMonths(1))
                .store(store)
                .build();

        Offer offer = offerService.create(offerSample);

        ProductOffer productOffer = productOfferService.create(ProductOffer.builder().offer(offer)
                .product(productSample)
                .build());
       // Save the store or perform any other operations as needed
        ProductOffer productOfferSample = productOfferService.create(productOffer);
        logger.info("Created Offer: {}", productOfferSample);
    }
}
