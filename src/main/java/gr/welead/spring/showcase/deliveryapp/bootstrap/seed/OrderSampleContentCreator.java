package gr.welead.spring.showcase.deliveryapp.bootstrap.seed;

import gr.welead.spring.showcase.deliveryapp.base.BaseComponent;
import gr.welead.spring.showcase.deliveryapp.model.*;
import gr.welead.spring.showcase.deliveryapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("orders")
@RequiredArgsConstructor
public class OrderSampleContentCreator extends BaseComponent implements CommandLineRunner {
    private final CustomerService customerService;
    private final StoreService storeService;
    private final ProductService productService;
    private final OrderService orderService;
    private final ProductOfferService productOfferService;
    @Override
    public void run(String... args) throws Exception {
        // Get all customers
        customerService.findAll().forEach(c -> logger.info("for each {} in order ", c));

        // We don't mind if a "find" method returns a null
        logger.info("Does customer exist? {}.", (customerService.findCustomerByAccount_Email("gianni@email.com").isPresent()));
        logger.info("Does customer exist? {}.", (customerService.findCustomerByAccount_Email("non-existing@gmail.com").isPresent()));

        //Display all stores by category
        List<Store> stores = storeService.findByCategory(StoreCategory.COFFEE_AND_BEVERAGES);
        logger.info("Display stores {} ", stores);

        //Find store by name
        Store store = storeService.findByName("DaVinci cafe");
         logger.info("The store selected {} ",store);

        //Test case1: Add products from the same store

        // Load customer and create an order by adding/updating/removing content before checking it out
        Optional<Customer> firstCustomer = customerService.findCustomerByAccount_Email("gianni@email.com");
        if(firstCustomer.isPresent()){
            Order firstOrder = orderService.initiateOrder(firstCustomer.get(), store);

            // Add item(s) both existing and non-existing
            orderService.addItemFromMenu(store,firstOrder,productService.findByName("Cappuccino"),1);
            orderService.addItemFromMenu(store,firstOrder,productService.findByName("Latte"),1);
            orderService.addItemFromMenu(store,firstOrder,productService.findByName("Latte"),1);
            orderService.addItemFromMenu(store,firstOrder, productService.findByName("Croissant"),2);

            // Add a non-existing product in the store
            orderService.addItemFromMenu(store,firstOrder, productService.findByName("Coca cola"),1);

            // Update item(s)
            orderService.updateItem(store,firstOrder, productService.findByName("Cappuccino"),3);
            orderService.addItemFromMenu(store,firstOrder, productService.findByName("Croissant"),1);
            // Remove item(s)
            orderService.removeItem(store,firstOrder,productService.findByName("Latte"));

            //Display order service till now //TO DO work a better display
            orderService.cart(firstOrder);
//            //Checkout
            Order orderPersisted = orderService.checkout(firstOrder, PaymentMethod.CASH, "WELEAD5MG874RJS");
//            //Display my order
            orderService.displayOrderServiceStatus(firstOrder);
            //Change order status to simulate delivery
            orderService.changeOrderStatus(orderPersisted);

        }

        //Test case2: Adding products from different stores
//         Load customer and create an order by adding/updating/removing content before checking it out

        Optional<Customer> secondCustomer = customerService.findCustomerByAccount_Email("nikos.papadopoulos@email.com");
        if(secondCustomer.isPresent()){
            Order secondOrder = orderService.initiateOrder(secondCustomer.get(),store);

            // Add item(s) both existing and non-existing
            orderService.addItemFromMenu(store,secondOrder,productService.findByName("Cappuccino"),1);
            orderService.addItemFromMenu(store,secondOrder,productService.findByName("Latte"),1);
            orderService.addItemFromMenu(store,secondOrder,productService.findByName("Latte"),1);
            // Add a non-existing product in the store
            orderService.addItemFromMenu(store,secondOrder, productService.findByName("Bikos kola"),1);

            // Add a existing product from another store
            //Find store by name
            Store store2 = storeService.findByName("Coffee Haven");
            logger.info("The store selected {} ",store2);
            //Add item from another store
            orderService.addItemFromMenu(store2,secondOrder, productService.findByName("Espresso"),2);
            //Display order service till now
            orderService.cart(secondOrder);
            //Add item from another store again
            orderService.updateItem(store,secondOrder, productService.findByName("Latte"),2);
            //Display order service till now
            orderService.cart(secondOrder);
            //Add item form another store
            orderService.addItemFromMenu(store2,secondOrder, productService.findByName("Espresso"),2);
            orderService.updateItem(store2,secondOrder, productService.findByName("Chocolate Fondant"),1);
            orderService.addItemFromMenu(store2,secondOrder, productService.findByName("Orange Juice"),2);
            //Display order service till now, I feel like I don't have enough money :/
            orderService.cart(secondOrder);
            // Update item(s)
            orderService.updateItem(store2,secondOrder, productService.findByName("Orange Juice"),1);
            //Display order till now
            orderService.cart(secondOrder);
            // Remove item(s)
            orderService.removeItem(store2,secondOrder,productService.findByName("Orange Juice"));
            //Display order till now
            orderService.cart(secondOrder);
            //Checkout
            orderService.checkout(secondOrder, PaymentMethod.CASH,"WELEAD5MG567TYH");
            //Display my order
            orderService.displayOrderServiceStatus(secondOrder);
        }

//        //3rd customer
        Optional<Customer> thirdCustomer = customerService.findCustomerByAccount_Email("alice.johnson@email.com");
        if(thirdCustomer.isPresent()){
            Order thirdOrder = orderService.initiateOrder(thirdCustomer.get(),store);

            // Add item(s) both existing and non-existing
            orderService.addItemFromMenu(store,thirdOrder,productService.findByName("Cappuccino"),1);
            orderService.updateItem(store,thirdOrder,productService.findByName("Baguette"),1);
            //Display my order
            orderService.cart(thirdOrder);
            //Now time to pay , I don't have coupons :(
            orderService.checkout(thirdOrder, PaymentMethod.CREDIT_CARD, null);
            //Display my order
            orderService.displayOrderServiceStatus(thirdOrder);
        }

        //4th customer, a hungry one!
        Store pizzaStore = storeService.findByName("Cazza De la pizza");
        Optional<Customer> fourthCustomer = customerService.findCustomerByAccount_Email("maria.papa@email.com");
        if(fourthCustomer.isPresent()){
            Order fourthOrder = orderService.initiateOrder(fourthCustomer.get(),pizzaStore);
            //Get me Cazza De la pizza menu pls~!
            List<Product> menu = storeService.getMenuByStoreId(pizzaStore.getId());

            menu.forEach(p-> {
                logger.info("Menu: {} ",p.getName());
            });
            //I want to eat Bolognese Pasta
            orderService.addItemFromMenu(pizzaStore, fourthOrder, productService.findByName("Bolognese Pasta"),1);
            //Display my order
            orderService.cart(fourthOrder);
            //Now time to pay , I don't have coupons :(
            orderService.checkout(fourthOrder, PaymentMethod.CREDIT_CARD, null);
            //Display my order
            orderService.displayOrderServiceStatus(fourthOrder);
        }

//        //5th customer, a offer chaser!
//        //Display me the offers first!
        List<ProductOffer> allProductOffers = productOfferService.findAllProductOffers();
        allProductOffers.forEach(p-> {
            logger.info("Offers for you: {} and offer type: {}  ", p.getProduct().getName(), p.getOffer().getOfferType());
        });
        //Great! I'll buy a latter and give the other to my friend!
        Product productToBuy = allProductOffers.get(0).getProduct();
        Offer offer = allProductOffers.get(0).getOffer();
        Store storeWithOffer = storeService.findByOffersContains(offer);

//        logger.warn("store Sells {} ", storeWithOffer.getName());
        Optional<Customer> fifthCustomer = customerService.findCustomerByAccount_Email("maria.papa@email.com");
        if(fifthCustomer.isPresent()){
            Order fifthOrder = orderService.initiateOrder(fifthCustomer.get(), storeWithOffer);
            orderService.addItemFromMenu(storeWithOffer, fifthOrder, productToBuy,1);
            orderService.cart(fifthOrder);
            //Now time to pay, I don't have coupon :(
            orderService.checkout(fifthOrder,PaymentMethod.CASH,null);
        }
    }
}
