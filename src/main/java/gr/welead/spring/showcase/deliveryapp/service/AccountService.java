package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Account;
import gr.welead.spring.showcase.deliveryapp.model.Role;

import java.util.List;
import java.util.Optional;

public interface AccountService extends BaseService<Account, Long>{
    Optional<Account> findByEmail(String email);

    List<Account> findByLastName(String lastName);

    List<Account> findByRole(Role role);
}
