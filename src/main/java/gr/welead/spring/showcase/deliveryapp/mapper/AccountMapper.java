package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.Account;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.AccountResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface AccountMapper extends BaseMapper<Account,AccountResource>{
}
