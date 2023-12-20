package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Product;

public interface ProductRepository extends BaseRepository<Product, Long> {
    Product findBySerial(final String serial);
}
