package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import gr.welead.spring.showcase.deliveryapp.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    //view productcategory by description
    public List<ProductCategory> findByDescription(String description){
        List<ProductCategory> productsByDescription = productCategoryRepository.findByDescription(description);
        return productsByDescription;

    }

    //add a product category

    public void addProductCategory(ProductCategory productCategory){
        productCategoryRepository.save(productCategory);
        //saves topic to the database
    }

    //view all product categories
    public List<ProductCategory> getAllProductCategories() {
        List<ProductCategory> productCategories = new ArrayList<>();
        productCategoryRepository.findAll()
                .forEach(productCategories::add);
        return productCategories;
    }
}
