package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.AverageTime;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.AverageTimeResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface AverageTimeMapper extends BaseMapper<AverageTime, AverageTimeResource>{
}
