package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.RoyaltyProgram;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.RoyaltyProgramResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface RoyaltyProgramMapper extends BaseMapper<RoyaltyProgram, RoyaltyProgramResource> {
}
