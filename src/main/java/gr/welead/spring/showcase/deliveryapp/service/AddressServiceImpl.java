package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Address;
import gr.welead.spring.showcase.deliveryapp.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService{
    final AddressRepository addressRepository;
    @Override
    protected JpaRepository<Address, Long> getRepository() {
        return addressRepository;
    }

    @Override
    public List<Address> sortByCity(String city) {
        return addressRepository.findByCity(city);
    }
}
