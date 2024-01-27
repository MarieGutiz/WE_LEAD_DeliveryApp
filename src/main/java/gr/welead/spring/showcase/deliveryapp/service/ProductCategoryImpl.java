package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import gr.welead.spring.showcase.deliveryapp.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryImpl extends BaseServiceImpl<ProductCategory> implements ProductCategoryService {
    final ProductCategoryRepository productCategoryRepository;

    @Override
    protected JpaRepository<ProductCategory, Long> getRepository() {
        return productCategoryRepository;
    }

    //view productcategory by description
    public List<ProductCategory> findByDescription(String description) {
        List<ProductCategory> productsByDescription = productCategoryRepository.findByDescription(description);
        return productsByDescription;

    }

    @Override
    public Optional<ProductCategory> findById(Long id) {
        return Optional.empty();
    }

    //add a product category

    public void addProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
        //saves  to the database
    }

    //view all product categories
    public List<ProductCategory> getAllProductCategories() {
        List<ProductCategory> productCategories = new ArrayList<>();
        productCategoryRepository.findAll()
                .forEach(productCategories::add);
        return productCategories;
    }


    //get productcategory by id
    public Optional<ProductCategory> getProductCategoryById(Long id) {
        return productCategoryRepository.findById(id);
    }

}
