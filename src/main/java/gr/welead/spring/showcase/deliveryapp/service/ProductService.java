package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductService extends BaseService<Product, Long> {
    Product findBySerial(final String serial);

    Product findByName(final String name);

    List<Product> findByCategory(final ProductCategory productCategory);

    List<Product> findAllByIdIn(List<Long> menu);

    Product findById(Long id);

    List<Product> getAllProducts();

    Optional<Product> getProduct(Long id);

    void addProduct(Product product);

    void deleteProduct(Long id);

    Product createProductWithCategory(Product product, Long categoryId);

    Product createProductWithStoreAndCategory(Product product, Long storeId, Long categoryId);

    Product findProductByName(String name);

    List<Product> getProductsByCategory(ProductCategory productCategory);
}
