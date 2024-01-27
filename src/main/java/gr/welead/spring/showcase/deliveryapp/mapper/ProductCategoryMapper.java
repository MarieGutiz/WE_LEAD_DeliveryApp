package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.ProductCategoryResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface ProductCategoryMapper extends BaseMapper<ProductCategory, ProductCategoryResource>{
}
