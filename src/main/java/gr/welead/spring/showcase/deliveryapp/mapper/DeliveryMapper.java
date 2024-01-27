package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.Delivery;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.DeliveryResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface DeliveryMapper extends BaseMapper<Delivery, DeliveryResource>{
}
