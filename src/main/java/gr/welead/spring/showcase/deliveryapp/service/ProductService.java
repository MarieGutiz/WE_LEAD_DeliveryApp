package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import gr.welead.spring.showcase.deliveryapp.repository.ProductCategoryRepository;
import gr.welead.spring.showcase.deliveryapp.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
//
//public class ProductService {
//    private final ProductRepository productRepository;
//    private final ProductCategoryRepository productCategoryRepository;
//
//    public ProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
//        this.productRepository = productRepository;
//        this.productCategoryRepository = productCategoryRepository;
//    }
//
//    //view all products
//    public List<Product> getAllProducts() {
//        List<Product> products = new ArrayList<>();
//        productRepository.findAll()
//                .forEach(products::add);
//        return products;
//    }
//
//    //add new product without category
//    public void addProduct(Product product) {
//        productRepository.save(product);
//        //saves topic to the database
//    }
//
//    //find product by id
//
//    public Optional<Product> getProduct(Long id) {
//        return productRepository.findById(id);
//    }
//
//    //delete a product
//
//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }
//
//
//    //create product with category
//    public Product createProductWithCategory(Product product, Long categoryId) {
//        Optional<ProductCategory> categoryOptional = productCategoryRepository.findById(categoryId);
//        // if the category exists
//        if (categoryOptional.isPresent()) {
//            ProductCategory category = categoryOptional.get();
//            product.setCategory(category);
//            return productRepository.save(product);
//        } else {
//            // if the category does not exist
//            throw new IllegalArgumentException("Category with ID " + categoryId + " not found.");
//        }
//    }
//
//    //  get a product by name
//    public Optional<Product> findProductByName(String name) {
//        return productRepository.findByName(name);
//    }
//
//
//    // view products by product category
//
//    public List<Product> getProductsByCategory(ProductCategory productCategory) {
//        return productRepository.findByProductCategory(productCategory);
//    }
//
//
//}
