package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.ProductOffer;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.ProductOfferResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface ProductOfferMapper extends BaseMapper<ProductOffer, ProductOfferResource>{
}
