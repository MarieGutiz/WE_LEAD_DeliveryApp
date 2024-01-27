package gr.welead.spring.showcase.deliveryapp.mapper;

import gr.welead.spring.showcase.deliveryapp.model.Coupon;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.CouponResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CouponMapper extends BaseMapper<Coupon, CouponResource> {
}
