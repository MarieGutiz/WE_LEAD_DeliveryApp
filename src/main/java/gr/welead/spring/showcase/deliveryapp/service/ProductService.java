package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;

import java.util.List;

public interface ProductService extends BaseService<Product, Long>{
    Product findBySerial(final String serial);

    Product findByName(final String name);

    List<Product> findByCategory(final ProductCategory productCategory);

    List<Product> findAllByIdIn(List<Long> menu);

    Product findById(Long id);
}
