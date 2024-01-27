package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Customer;
import gr.welead.spring.showcase.deliveryapp.model.Order;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends BaseService<Customer, Long> {
    Optional<Customer> findCustomerWithRoyaltyProgram(Long customerId);

    List<Order> findHistoryOrder();

    Optional<Customer> findCustomerByAccount_Email(String email);

    void assignPointsToCustomer(Customer customer, int points, String description);

    Customer findById(Long id);

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long customerId);

    Optional<Customer> getCustomerByEmail(String email);

    Optional<Customer> getCustomersByName(String firsName, String lastName);

    Optional<Customer> getCustomerByPhoneNumber(String phoneNumber);

    Customer createCustomer(Customer customer);

    void deleteCustomer(Long customerId);

    Customer updateCustomer(Long customerId, Customer updatedCustomer);

}
