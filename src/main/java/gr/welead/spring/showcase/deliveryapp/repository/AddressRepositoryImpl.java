package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class AddressRepositoryImpl extends BaseRepositoryImpl<Address> implements AddressRepository {
    private final ConcurrentHashMap<Long, Address> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, Address> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }


    @Override
    public List<Address> sortByCity(String city) {
        return getStorage().values().stream()
                .filter(address -> address.getCity().equals(city))
                .collect(Collectors.toList());
    }
}