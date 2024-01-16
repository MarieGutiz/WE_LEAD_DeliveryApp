package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
    public Optional<Customer> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<Customer> findByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findById(Long customerId) {
        return Optional.empty();
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }
}
