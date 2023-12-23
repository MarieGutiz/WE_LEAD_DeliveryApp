package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.ProductOffer;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductOfferImpl extends BaseRepositoryImpl<ProductOffer> {
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
}
