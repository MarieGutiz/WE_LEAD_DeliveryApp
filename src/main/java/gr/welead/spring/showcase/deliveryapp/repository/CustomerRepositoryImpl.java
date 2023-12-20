package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer> implements CustomerRepository {

    protected final ConcurrentHashMap<Long, Customer> storage = new ConcurrentHashMap<>();
    protected final AtomicLong sequence = new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, Customer> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }

    @Override
    public Customer findCustomerWithRoyaltyProgram(Long customerId) {
        return getStorage().values()
                .stream()
                .filter(customer -> customer.getId().equals(customerId))
                .map(customer -> (Customer) customer) // Cast to Customer
                .filter(customer -> customer.getRoyaltyProgram() != null && customer.getRoyaltyProgram().getPoints() > 0)
                .findFirst()
                .orElse(null);
    }
}
