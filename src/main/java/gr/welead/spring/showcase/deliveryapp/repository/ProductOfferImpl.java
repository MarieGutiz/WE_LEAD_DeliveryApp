package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Offer;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductOffer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductOfferImpl extends BaseRepositoryImpl<ProductOffer> implements ProductOfferRepository {
    protected ConcurrentHashMap<Long, ProductOffer> storage = new ConcurrentHashMap<>();

    protected AtomicLong sequence = new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, ProductOffer> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }

    @Override
    public List<ProductOffer> findByProduct(Product product) {
        return getStorage().values()
                .stream()
                .filter(productOffer -> productOffer.getProduct().equals(product))
                .toList();
    }

    @Override
    public List<ProductOffer> findByOffer(Offer offer) {
        return getStorage().values()
                .stream()
                .filter(productOffer -> productOffer.getOffer().equals(offer))
                .toList();
    }
}