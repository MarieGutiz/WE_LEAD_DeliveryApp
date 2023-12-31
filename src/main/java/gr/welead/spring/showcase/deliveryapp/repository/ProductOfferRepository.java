package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Offer;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductOffer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOfferRepository extends BaseRepository<ProductOffer, Long> {
    List<ProductOffer> findByProduct(Product product);

    List<ProductOffer> findByOffer(Offer offer);
}
