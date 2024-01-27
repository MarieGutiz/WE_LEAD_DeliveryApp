package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Customer;
import gr.welead.spring.showcase.deliveryapp.model.Order;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends BaseService<Customer, Long>{
    Optional<Customer> findCustomerWithRoyaltyProgram(Long customerId);
    List<Order> findHistoryOrder();
    Optional<Customer> findCustomerByAccount_Email(String email);
    void assignPointsToCustomer(Customer customer, int points, String description);

    Customer findById(Long id);
}
