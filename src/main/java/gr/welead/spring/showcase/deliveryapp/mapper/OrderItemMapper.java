package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.OrderItem;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.OrderItemResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemResource>{
}
