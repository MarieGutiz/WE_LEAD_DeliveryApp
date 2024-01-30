package gr.welead.spring.showcase.deliveryapp.controller;

import gr.welead.spring.showcase.deliveryapp.mapper.BaseMapper;
import gr.welead.spring.showcase.deliveryapp.mapper.ProductCategoryMapper;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import gr.welead.spring.showcase.deliveryapp.service.BaseService;
import gr.welead.spring.showcase.deliveryapp.service.ProductCategoryService;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.ProductCategoryResource;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;


    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }



    @RequestMapping("/productcategories/{description}")
    public List<ProductCategory> getProductCategory(@PathVariable String description) {
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
    public void addProductCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.addProductCategory(productCategory);
    }

}
