package gr.welead.spring.showcase.deliveryapp.controller;

import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import gr.welead.spring.showcase.deliveryapp.service.ProductCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }


    @RequestMapping("/productcategories/{description}")
    public List<ProductCategory> getProductCategory(@PathVariable String description){
        return productCategoryService.findByDescription(description);
    }



    @GetMapping("/productcategories")
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        if (!productCategories.isEmpty()) {
            return new ResponseEntity<>(productCategories, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/productcategories")
    public void addProductCategory(@RequestBody ProductCategory productCategory){
        productCategoryService.addProductCategory(productCategory);
    }
}
