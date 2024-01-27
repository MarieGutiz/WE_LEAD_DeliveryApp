package gr.welead.spring.showcase.deliveryapp.repository;


import gr.welead.spring.showcase.deliveryapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query to fetch a customer with its associated royalty program
    @Query("SELECT c FROM Customer c JOIN FETCH c.royaltyProgram WHERE c.id = :customerId")
    Optional<Customer> findCustomerWithRoyaltyProgram(@Param("customerId") Long customerId);

    // Spring Data JPA will generate the query for this method based on the method name
    Optional<Customer> findCustomerByAccount_Email(String email);

}
