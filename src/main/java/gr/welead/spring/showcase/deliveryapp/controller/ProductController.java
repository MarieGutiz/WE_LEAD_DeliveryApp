package gr.welead.spring.showcase.deliveryapp.controller;

import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.service.ProductCategoryService;
import gr.welead.spring.showcase.deliveryapp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class ProductController {
    private final ProductService productService;

    private final ProductCategoryService productCategoryService;



    public ProductController(ProductService productService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }


    @RequestMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping("products/{id}")
    public Optional<Product> getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }



    //add product without category
    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }



    @PostMapping("/products/{categoryId}/productswithcategory")
    public ResponseEntity<Product> createProductWithCategory(@RequestBody Product product, @PathVariable Long categoryId) {
        Product createdProduct = productService.createProductWithCategory(product, categoryId);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

}
