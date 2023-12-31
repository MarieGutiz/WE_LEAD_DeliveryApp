package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {
    List<Address> sortByCity(String city);
}