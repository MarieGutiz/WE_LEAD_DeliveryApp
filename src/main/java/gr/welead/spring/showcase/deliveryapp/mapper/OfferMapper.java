package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.Offer;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.OfferResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface OfferMapper extends BaseMapper<Offer, OfferResource>{
}
