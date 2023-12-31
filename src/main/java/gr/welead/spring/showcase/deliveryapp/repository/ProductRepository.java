package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
    Product findBySerial(final String serial);

    List<Product> findByCategory(final ProductCategory productCategory);

    public List<Product> findAllByIdIn(List<Long> ids);
}