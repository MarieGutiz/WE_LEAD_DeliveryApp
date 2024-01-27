package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Account;
import gr.welead.spring.showcase.deliveryapp.model.Role;
import gr.welead.spring.showcase.deliveryapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    protected JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<Account> findByLastName(String lastName) {
        return accountRepository.findByLastName(lastName);
    }

    @Override
    public List<Account> findByRole(Role role) {
        return accountRepository.findByRole(role);
    }
}
