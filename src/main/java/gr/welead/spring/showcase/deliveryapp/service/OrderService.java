package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.*;
import gr.welead.spring.showcase.deliveryapp.transfer.OrderDetailsReportByCustomerId;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderService extends BaseService<Order, Long> {

    List<Order> findByStore(Store store);

    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);//search for my last order

    void clearOrder(Order order);

    Order initiateOrder(Customer customer, Store store);


    //Add one or more items from the store's menu to the order.
    //● Create an order containing items only from the same store.
    //● In case we change to another store and add one of its items, the order should be cleared before
    //adding the new item(s)
    void addItemFromMenu(Store store, Order order, Product product, int quantity);

    void updateItem(Store store, Order order, Product product, int quantity);

    void removeItem(Store store, Order order, Product product);

    Order checkout(Order order, PaymentMethod paymentMethod, String coupon);

    //Retrieved all placed orders
    List<Order> findAllPlacedOrders();

    void displayOrderServiceStatus(Order order);

    void cart(Order order);

    @Transactional
    void changeOrderStatus(Order order);

    List<Order> findOrdersByCustomer(Customer customer);

    List<OrderDetailsReportByCustomerId[]> findOrdersDetailsByCustomerId(@Param("customerId") Long customerId);
}
