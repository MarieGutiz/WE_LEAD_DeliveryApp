package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Customer;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
    List<Customer> findByName(String firstName, String lastName);
    Optional<Customer> findByPhoneNumber(String phoneNumber);
    Optional<Customer> findById(Long customerId);

    Customer save(Customer customer);

}

