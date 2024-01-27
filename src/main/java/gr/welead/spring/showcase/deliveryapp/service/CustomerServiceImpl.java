package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Customer;
import gr.welead.spring.showcase.deliveryapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//public class CustomerServiceImpl implements CustomerService {
//
//    private final CustomerRepository customerRepository;
//
//    public CustomerServiceImpl(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    @Override
//    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
//
//    @Override
//    public Optional<Customer> getCustomerById(Long customerId) {
//        return customerRepository.findById(customerId);
//    }
//
//    @Override
//    public Optional<Customer> getCustomerByEmail(String email) {
//        return customerRepository.findCustomerByAccount_Email(email);
//    }
//
//    @Override
//    public List<Customer> getCustomersByName(String firstName, String lastName) {
//        return null;
//    }
//
//    @Override
//    public Optional<Customer> getCustomerByPhoneNumber(String phoneNumber) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Customer createCustomer(Customer customer) {
//        return null;
//    }
//
//    @Override
//    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
//        return null;
//    }
//
//    @Override
//    public void deleteCustomer(Long customerId) {
//
//    }
//
//
//}
