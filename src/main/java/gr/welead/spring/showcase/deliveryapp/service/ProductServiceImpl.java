package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import gr.welead.spring.showcase.deliveryapp.repository.ProductCategoryRepository;
import gr.welead.spring.showcase.deliveryapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
    final ProductRepository productRepository;
    final ProductCategoryRepository productCategoryRepository;

    @Override
    protected JpaRepository<Product, Long> getRepository() {
        return productRepository;
    }

    @Override
    public Product findBySerial(String serial) {
        return productRepository.findBySerial(serial);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findByCategory(ProductCategory productCategory) {
        return productRepository.findByCategory(productCategory);
    }

    @Override
    public List<Product> findAllByIdIn(List<Long> menu) {
        return productRepository.findAllByIdIn(menu);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    //view all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll()
                .forEach(products::add);
        return products;
    }

    //add new product without category
    public void addProduct(Product product) {
        productRepository.save(product);
        //saves topic to the database
    }

    //find product by id

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    //delete a product

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


    //create product with category
    public Product createProductWithCategory(Product product, Long categoryId) {
        Optional<ProductCategory> categoryOptional = productCategoryRepository.findById(categoryId);
        // if the category exists
        if (categoryOptional.isPresent()) {
            ProductCategory category = categoryOptional.get();
            product.setCategory(category);
            return productRepository.save(product);
        } else {
            // if the category does not exist
            throw new IllegalArgumentException("Category with ID " + categoryId + " not found.");
        }
    }

    //  get a product by name
    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }


    // view products by product category

    public List<Product> getProductsByCategory(ProductCategory productCategory) {
        return productRepository.findByCategory(productCategory);
    }

}
