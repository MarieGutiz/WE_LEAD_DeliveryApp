package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.Address;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.AddressResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface AddressMapper extends BaseMapper<Address, AddressResource>{
}
