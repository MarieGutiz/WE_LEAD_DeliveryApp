package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService extends BaseService<ProductCategory, Long> {
    List<ProductCategory> findByDescription(String description);

    Optional<ProductCategory> findById(Long id);

    List<ProductCategory> getAllProductCategories();

    void addProductCategory(ProductCategory productCategory);

    Optional<ProductCategory> getProductCategoryById(Long id);
}
