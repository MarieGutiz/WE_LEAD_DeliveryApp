package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.Order;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.OrderResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface OrderMapper extends BaseMapper<Order, OrderResource>{
}
