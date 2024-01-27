package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.*;
import gr.welead.spring.showcase.deliveryapp.repository.CouponRepository;
import gr.welead.spring.showcase.deliveryapp.repository.CustomerRepository;
import gr.welead.spring.showcase.deliveryapp.repository.OrderRepository;
import gr.welead.spring.showcase.deliveryapp.repository.ProductOfferRepository;
import gr.welead.spring.showcase.deliveryapp.transfer.OrderDetailsReportByCustomerId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {//To Do
    final OrderRepository orderRepository;
    final CouponRepository couponRepository;
    final CustomerRepository customerRepository;
    final ProductOfferRepository productOfferRepository;
    final CustomerService customerService;
    final StoreService storeService;

    @Override
    protected JpaRepository<Order, Long> getRepository() {
        return orderRepository;
    }

    @Override
    public List<Order> findByStore(Store store) {
        return orderRepository.findByStore(store);
    }

    @Override
    public List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }

    @Override
    public void clearOrder(Order order) {
        // Implement the logic to clear the order (remove items, reset total, etc.)

        order.getOrderItems().clear();//use try/catch
        logger.warn("Order got cleared");

    }

    @Override
    public Order initiateOrder(final Customer customer, Store store) {
        return Order.builder().customer(customer).store(store).orderStatus(OrderStatus.RAISED).build();
    }

    @Override
    public void addItemFromMenu(final Store store, final Order order, final Product product, final int quantity) {
        if (checkNullability(store, order, product)) {
            return;
        }
        // Check and clear order if the store is different
        if (checkAndClearOrderIfDifferentStore(order, store)) { //The store given is not the same store I created the order
            // Clear the order if the product belongs to a different store
            // Add the new item to the cleared order
            order.getOrderItems().add(newOrderItem(order, product, quantity));
            order.setStore(store);//My new store is the last one!
            logger.trace("Order cleared due to changing store. Product[{}] added to Order[{}]", product, order);
            return;
        }

        // Check if the product belongs to the same store
        if (isProductInStore(store, product)) {
            boolean increasedQuantity = false;

            // If product is already contained in the order, don't add it again, just increase the quantity accordingly
            for (OrderItem oi : order.getOrderItems()) {
                if (oi.getProduct().getName().equals(product.getName())) {
                    oi.setQuantity(oi.getQuantity() + quantity);
                    logger.trace("product was already found in order {} ", oi.getProduct().getName());
                    increasedQuantity = true;
                    break;
                }
            }
            if (!increasedQuantity) {
                order.getOrderItems().add(newOrderItem(order, product, quantity));
//                logger.info("Get order items {} ", order.getOrderItems());
            }
            logger.trace("Product[{}] added to Order[{}]", product, order);
        }

    }

    @Override
    public void updateItem(final Store store, final Order order, final Product product, final int quantity) {
        if (checkNullability(store, order, product)) {
            return;
        }
        if (checkAndClearOrderIfDifferentStore(order, store)) { //the store given is not the same store I created the order
            order.getOrderItems().add(newOrderItem(order, product, quantity));
            order.setStore(store);
            logger.trace("Order cleared due to changing store. Product[{}] added to Order[{}]", product, order);
            return;
        }
        if (isProductInStore(store, product)) {
            order.getOrderItems().removeIf(oi -> oi.getProduct().getName().equals(product.getName()));
            order.getOrderItems().add(newOrderItem(order, product, quantity));
            logger.trace("Product[{}] updated in Order[{}]", product, order);
        } else {
            logger.warn("Product[{}] does not belong to the store. Unable to update in Order[{}]", product, order);
        }
    }

    @Override
    public void removeItem(final Store store, final Order order, final Product product) {
        if (checkNullability(store, order, product)) {
            return;
        }

        if (isProductInStore(store, product)) {
            order.getOrderItems().removeIf(oi -> oi.getProduct().getName().equals(product.getName()));
            logger.trace("Product[{}] removed from Order[{}]", product, order);
        } else {
            logger.warn("Product[{}] does not belong to the store. Unable to remove from Order[{}]", product, order);
        }
    }

    @Override
    public Order checkout(final Order order, final PaymentMethod paymentMethod, final String coupon) {
        if (!validate(order, order.getStore(), paymentMethod)) {
            logger.warn("Order should have customer, order items, should belong to store existing, and payment type defined before being able to " +
                    "checkout the order.");
            return null;
        }

        // Validate and apply coupon
        Optional<Coupon> optionalCoupon = couponRepository.validateAndApplyCoupon(coupon, LocalDateTime.now());
        Coupon couponApplied;
        if (optionalCoupon.isPresent()) {
            couponApplied = optionalCoupon.get();
            // Set the couponApplied to used
            couponApplied.setIsUsed(true);
            couponRepository.save(couponApplied); // Save the updated coupon status


        } else {
            logger.warn("Coupon validation failed or coupon not found.");
            couponApplied = null;
        }


        // Set all order fields with proper values
        order.setPaymentMethod(paymentMethod);
        order.setDelivery(calculateDelivery());
        order.setAppliedCoupon(couponApplied);
        order.setSubTotal(calculateSubtotal(order));
        order.setTotal(calculateTotal(order));
        order.setOrderDate(LocalDateTime.now()); // time of checkout
        order.setOrderStatus(OrderStatus.RAISED);

        // Assign 100 points to the customer for his/her purchase
        customerService.assignPointsToCustomer(order.getCustomer(), 100, "Royalty points");

        return create(order);
    }

    private BigDecimal calculateSubtotal(Order order) {
        BigDecimal subtotal = BigDecimal.ZERO;

        // Calculate subtotal based on order items
        for (OrderItem orderItem : order.getOrderItems()) {
            BigDecimal itemPrice = orderItem.getPrice();
            int quantity = orderItem.getQuantity();
            subtotal = subtotal.add(itemPrice.multiply(BigDecimal.valueOf(quantity)));
        }
        //Check for offers TO DO
        // Apply product offers
        for (OrderItem orderItem : order.getOrderItems()) {
            Product product = orderItem.getProduct();
            BigDecimal itemPrice = orderItem.getPrice();
            int quantity = orderItem.getQuantity();

            // Check if there is an offer for the product
            if (hasProductOffer(product)) {
                Offer offer = getProductOffer(product).getOffer();

                // Apply offer based on its type
                switch (offer.getOfferType()) {
                    case BOGO:
                        // Buy One, Get One Free
                        int freeQuantity = quantity / (offer.getBuyQuantity() + offer.getFreeQuantity()) * offer.getFreeQuantity();
                        BigDecimal discountedAmount = itemPrice.multiply(BigDecimal.valueOf(freeQuantity));
                        subtotal = subtotal.subtract(discountedAmount);
                        logger.debug("You got {} type with {} extra product ", offer.getOfferType(), offer.getFreeQuantity());
                        break;
                    case DISCOUNT://TO DO
                        // Percentage discount
                        BigDecimal discountPercentage = BigDecimal.valueOf(offer.getDiscount() / 100.0);
                        BigDecimal discountAmount = itemPrice.multiply(BigDecimal.valueOf(quantity)).multiply(discountPercentage);
                        subtotal = subtotal.subtract(discountAmount);
                        logger.debug("You got {} type with {} ", offer.getOfferType(), discountPercentage);

                        break;
                    // Add more cases for other offer types if needed
                }
            }
        }


        // Apply coupon discount if available
        if (order.getAppliedCoupon() != null) {
            logger.debug("Subtotal before applying coupon: {}", subtotal);
            Coupon coupon = order.getAppliedCoupon();

            if (coupon.getDiscount() != null) {
                // If the coupon provides a percentage discount
                BigDecimal discountPercentage = BigDecimal.valueOf(coupon.getDiscount() / 100.0);
                BigDecimal discountAmount = subtotal.multiply(discountPercentage);
                subtotal = subtotal.subtract(discountAmount);

                // Logging
                logger.info("Subtotal after applying coupon percentage discount ({}%): {}", coupon.getDiscount(), subtotal);
            } else if (coupon.getFixedAmount() != null) {
                // If the coupon provides a fixed amount discount
                subtotal = subtotal.subtract(coupon.getFixedAmount());

                // Logging
                logger.info("Subtotal after applying coupon fixed amount discount ({}): {}", coupon.getFixedAmount(), subtotal);
            }
        }


        // Logging
        logger.debug("Final subtotal: {}", subtotal);

        return subtotal;
    }

    private BigDecimal calculateTotal(Order order) {
        BigDecimal subtotal = order.getSubTotal();
        if (subtotal.equals(BigDecimal.ZERO)) return BigDecimal.ZERO; // Return if subtotal = 0

        BigDecimal total = subtotal;

        // Add delivery cost
        BigDecimal baseCost = order.getDelivery().getBaseCost();

        // Add additional charges, taxes, or fees as needed
        // For example, in Greece, the tax rate is 24%, you might do:
        BigDecimal taxRate = BigDecimal.valueOf(0.24);
        BigDecimal taxAmount = subtotal.multiply(taxRate).add(baseCost.multiply(taxRate));
        total = total.add(taxAmount);

        // Logging
        logger.debug("Total after adding delivery cost and taxes: {}", total);

        return total;
    }

    @Override
    public List<Order> findAllPlacedOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void cart(Order order) {
        if (!order.getOrderItems().isEmpty()) {
            logger.info("Order Items:");
            for (OrderItem orderItem : order.getOrderItems()) {
                Product product = orderItem.getProduct();
                String productName = product.getName();
                int quantity = orderItem.getQuantity();
                BigDecimal price = orderItem.getPrice();
                String offerInfo = "";

                // Check if the product has an associated offer
                if (hasProductOffer(product)) {
                    ProductOffer productOffer = getProductOffer(product);
                    Offer offer = productOffer.getOffer();

                    // Build offer details string
                    StringBuilder offerDetails = new StringBuilder(" (Offer: ");
                    offerDetails.append(offer.getName());

                    // Add offer type details
                    offerDetails.append(", Type: ").append(offer.getOfferType());

                    // Add additional details based on offer type
                    switch (offer.getOfferType()) {
                        case BOGO:
                            offerDetails.append(", Buy ").append(offer.getBuyQuantity())
                                    .append(", Get ").append(offer.getFreeQuantity()).append(" Free");
                            break;
                        case DISCOUNT:
                            offerDetails.append(", Discount: ").append(offer.getDiscount()).append("%");
                            break;
                        // Add more cases for other offer types if needed
                    }

                    offerDetails.append(")");
                    offerInfo = offerDetails.toString();
                }

                logger.info("  Product: {}, Quantity: {}, Price: {}{}", productName, quantity, price, offerInfo);
            }
        } else {
            logger.info("No items in the order.");
        }
    }

    private boolean checkNullability(Store store, Order order, Product product) {
        if (store == null) {
            logger.warn("Store is null therefore your order cannot proceed");
            return true;
        }
        if (order == null) {
            logger.warn("Order is null therefore it cannot be processed.");
            return true;
        }
        if (product == null) {
            logger.warn("Product is null therefore it cannot be added to an order.");
            return true;
        }
        return false;
    }

    private boolean validate(Order order, Store store, PaymentMethod paymentMethod) {
        return order != null && !order.getOrderItems().isEmpty() && order.getCustomer() != null && store != null && paymentMethod != null;
    }

    private OrderItem newOrderItem(Order order, Product product, int quantity) {
        return OrderItem.builder().product(product).quantity(quantity).price(product.getPrice())
                .order(order)
                .build();
    }

    private boolean checkAndClearOrderIfDifferentStore(Order order, Store store) {
        if (order.getStore() != store) {
            // Clear the order if the store is different
            logger.warn("Different stores: {} got {}", order.getStore(), store);
            clearOrder(order);
        }
        return order.getStore() != store;
    }

    private boolean isProductInStore(Store store, Product product) {//check if product exists in store
//        return store.getMenu() != null && store.getMenu().contains(product);
        return storeService.existsByMenuContaining(product);
    }

    @Override
    public void displayOrderServiceStatus(Order order) {
        if (order != null) {
            logger.info("Order Status: {}", order.getOrderStatus());
            logger.info("Order Date: {}", order.getOrderDate());
            logger.info("Customer: {} {} ", order.getCustomer().getAccount().getFirstName(), order.getCustomer().getAccount().getLastName());
            logger.info("Store: {}", order.getStore().getName());

            cart(order);

            logger.info("Subtotal: {}", order.getSubTotal().setScale(2, RoundingMode.HALF_UP));
            logger.info("Total: {} after taxes", order.getTotal().setScale(2, RoundingMode.HALF_UP));
        } else {
            logger.warn("Order is null. Unable to display status.");
        }
    }

    private Delivery calculateDelivery() {
        AverageTime sampleDeliveryTime = AverageTime.builder()
                .averageTime("00:08:00")
                .build();
        return Delivery.builder()
                .time(sampleDeliveryTime)
                .build();
    }

    //Simulate a delivery
    //Create a clock to change order status
    @Transactional
    public void changeOrderStatus(Order order) {//ver
        AverageTime preparationTime = order.getStore().getTime();
        AverageTime deliveryTime = order.getDelivery().getTime();

        // Create a ScheduledExecutorService to simulate the clock
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        // Set order status to IN_PROGRESS after waiting for 30 seconds
        executorService.schedule(() -> {
            order.setOrderStatus(OrderStatus.IN_PROGRESS);
            orderRepository.updateOrderStatus(order.getId(), OrderStatus.IN_PROGRESS);
            System.out.println("Order " + order.getId() + " is in progress.");
        }, 30, TimeUnit.SECONDS);

        // Calculate total preparation and delivery time in seconds
        long preparationSeconds = preparationTime.getLocalTime().toSecondOfDay();
        long deliverySeconds = deliveryTime.getLocalTime().toSecondOfDay();
        long totalSeconds = preparationSeconds + deliverySeconds;

        // Schedule a task to change order status to DELIVER after the total time
        executorService.schedule(() -> {
            order.setOrderStatus(OrderStatus.DELIVERED);
            orderRepository.updateOrderStatus(order.getId(), OrderStatus.DELIVERED);
            System.out.println("Order " + order.getId() + " has been delivered.");

            // Shutdown the executor service
            executorService.shutdown();
        }, totalSeconds, TimeUnit.SECONDS);
    }

    @Override
    public List<Order> findOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }

    @Override
    public List<OrderDetailsReportByCustomerId[]> findOrdersDetailsByCustomerId(Long customerId) {
        return orderRepository.findOrdersDetailsByCustomerId(customerId);
    }


    private boolean hasProductOffer(Product product) {
        // Check if the product has an associated offer
        return productOfferRepository.findProductOfferByProduct(product).isPresent();
    }


    private ProductOffer getProductOffer(Product product) {
        // Retrieve the product offer for the given product
        return productOfferRepository.findProductOfferByProduct(product).orElse(null);
    }

}