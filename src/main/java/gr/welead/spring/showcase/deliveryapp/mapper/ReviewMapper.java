package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.StoreReview;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.ReviewResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface ReviewMapper extends BaseMapper<StoreReview, ReviewResource>{
}
