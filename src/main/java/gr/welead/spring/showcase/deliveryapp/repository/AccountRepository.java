package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {

    public Account findByEmail(String email);

    List<Account> findByLastName(String lastName);

    List<Account> findByRole(String role);
}
