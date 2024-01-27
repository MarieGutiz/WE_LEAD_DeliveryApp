package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.ProductResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface ProductMapper extends BaseMapper<Product, ProductResource>{
}
