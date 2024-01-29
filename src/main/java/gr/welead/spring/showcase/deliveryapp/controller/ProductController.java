package gr.welead.spring.showcase.deliveryapp.controller;

import gr.welead.spring.showcase.deliveryapp.mapper.BaseMapper;
import gr.welead.spring.showcase.deliveryapp.mapper.ProductMapper;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
//import gr.welead.spring.showcase.deliveryapp.service.ProductCategoryService;
//import gr.welead.spring.showcase.deliveryapp.service.ProductService;
import gr.welead.spring.showcase.deliveryapp.service.BaseService;
import gr.welead.spring.showcase.deliveryapp.service.ProductCategoryService;
import gr.welead.spring.showcase.deliveryapp.service.ProductService;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController extends BaseController<Product, ProductResource>{
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final ProductCategoryService productCategoryService;



    @Override
    protected BaseService<Product,Long> getBaseService(){
        return productService;
    }

    @Override
    protected BaseMapper<Product, ProductResource> getMapper() {
        return productMapper;

    }


    //post product with product category and store
    //create?storeId=1&categoryId=1
    @PostMapping("/create")
    public ResponseEntity<Product> createProductWithStoreAndCategory(@RequestBody Product product,
                                                                     @RequestParam Long storeId,
                                                                     @RequestParam Long categoryId) {
        //Product createdProduct = ProductMapper.INSTANCE.toDomain(productResource);
        Product createdProductWithStoreAndCategory = productService.createProductWithStoreAndCategory(product, storeId, categoryId);
        return new ResponseEntity<>(createdProductWithStoreAndCategory, HttpStatus.CREATED);
    }




    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping("products/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }


    //add product without category
    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }


    //add product with category
    @PostMapping("/products/{categoryId}/productswithcategory")
    public ResponseEntity<Product> createProductWithCategory(@RequestBody Product product, @PathVariable Long categoryId) {
        Product createdProduct = productService.createProductWithCategory(product, categoryId);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


    //search product by name
    ///searchproducts?name=frappe
    @GetMapping("/searchproducts")
    public Product findProductByName(@RequestParam String name) {
        return productService.findProductByName(name);
    }


    //  search for products by product category
    @GetMapping("/productsbycategory/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        Optional<ProductCategory> productCategory = productCategoryService.getProductCategoryById(categoryId);
        if (productCategory.isPresent()) {
            List<Product> products = productService.getProductsByCategory(productCategory.get());
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}


