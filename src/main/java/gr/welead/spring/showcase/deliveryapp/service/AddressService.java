package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Address;

import java.util.List;


public interface AddressService extends BaseService<Address, Long>{
    List<Address> sortByCity(String city);
}
