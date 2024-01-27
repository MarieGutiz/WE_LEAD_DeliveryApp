package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Customer;
import gr.welead.spring.showcase.deliveryapp.model.Order;
import gr.welead.spring.showcase.deliveryapp.model.RoyaltyProgram;
import gr.welead.spring.showcase.deliveryapp.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {
    final CustomerRepository customerRepository;

    @Override
    protected JpaRepository<Customer, Long> getRepository() {
        return customerRepository;
    }


    @Override
    public Optional<Customer> findCustomerWithRoyaltyProgram(Long customerId) {
        return customerRepository.findCustomerWithRoyaltyProgram(customerId);
    }

    @Override
    public List<Order> findHistoryOrder() {
        return null;
    }

    @Override
    public Optional<Customer> findCustomerByAccount_Email(String email) {
        return customerRepository.findCustomerByAccount_Email(email);
    }

    @Override
    @Transactional
    public void assignPointsToCustomer(Customer customer, int pointsAssigned, String description) {
        customerRepository.findCustomerWithRoyaltyProgram(customer.getId()).ifPresent(customer1 -> {
            RoyaltyProgram royaltyProgram = customer1.getRoyaltyProgram();
            if (pointsAssigned > 0) {
                royaltyProgram.setPoints(royaltyProgram.getPoints() + pointsAssigned);
                royaltyProgram.setProgramDescription(description);
                customerRepository.save(customer);  // Save the updated customer with the assigned points
                logger.info("Assigned {} points to customer: {} in concept of {} ",
                        pointsAssigned, customer.getAccount().getFirstName(), description);
            } else {
                logger.warn("Invalid customer or points for points assignment.");
            }
        });
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return this.findCustomerByAccount_Email(email);
    }

    @Override
    public Optional<Customer> getCustomersByName(String firsName, String lastName) {
        return customerRepository.findByAccount_FirstNameAndAccount_LastName(firsName, lastName);
    }

    @Override
    public Optional<Customer> getCustomerByPhoneNumber(String phoneNumber) {
        return customerRepository.findCustomerByAccount_Address_PhoneNumber(phoneNumber);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Optional<Customer> customerToDelete = customerRepository.findById(customerId);
        customerToDelete.ifPresent(customerRepository::delete);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();

            // Update fields of existingCustomer with values from updatedCustomer
            // Note: You may want to add null checks or handle specific cases based on your requirements
            existingCustomer.setAccount(updatedCustomer.getAccount());
            existingCustomer.setRoyaltyProgram(updatedCustomer.getRoyaltyProgram());
            // ... update other fields as needed

            // Save the updated customer
            customerRepository.save(existingCustomer);

            return existingCustomer;
        } else {
            // Handle the case where the customer with the given ID is not found
            throw new EntityNotFoundException("Customer with id " + customerId + " not found");
        }
    }

}
