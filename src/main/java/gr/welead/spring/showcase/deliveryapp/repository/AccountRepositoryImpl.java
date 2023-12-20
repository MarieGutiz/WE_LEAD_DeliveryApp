package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AccountRepositoryImpl extends BaseRepositoryImpl<Account> implements AccountRepository {

    private final ConcurrentHashMap<Long, Account> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, Account> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }

    @Override
    public Account findByEmail(String email) {
        return getStorage().values()
                .stream()
                .filter(account -> account.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Account> findByLastName(String lastName) {
        return getStorage().values()
                .stream()
                .filter(account -> account.getLastName().equals(lastName))
                .toList();
    }

    @Override
    public List<Account> findByRole(String role) {
        return getStorage().values()
                .stream()
                .filter(account -> account.getRole().name().equalsIgnoreCase(role))
                .toList();
    }

}
