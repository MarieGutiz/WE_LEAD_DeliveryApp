package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Customer;
import gr.welead.spring.showcase.deliveryapp.model.Order;
import gr.welead.spring.showcase.deliveryapp.model.RoyaltyProgram;
import gr.welead.spring.showcase.deliveryapp.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService{
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
}
