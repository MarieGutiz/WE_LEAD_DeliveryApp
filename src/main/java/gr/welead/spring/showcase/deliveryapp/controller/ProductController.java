package gr.welead.spring.showcase.deliveryapp.controller;

import gr.welead.spring.showcase.deliveryapp.mapper.BaseMapper;
import gr.welead.spring.showcase.deliveryapp.mapper.ProductMapper;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
//import gr.welead.spring.showcase.deliveryapp.service.ProductCategoryService;
//import gr.welead.spring.showcase.deliveryapp.service.ProductService;
import gr.welead.spring.showcase.deliveryapp.model.Store;
import gr.welead.spring.showcase.deliveryapp.service.BaseService;
import gr.welead.spring.showcase.deliveryapp.service.ProductCategoryService;
import gr.welead.spring.showcase.deliveryapp.service.ProductService;
import gr.welead.spring.showcase.deliveryapp.service.StoreService;
import gr.welead.spring.showcase.deliveryapp.transfer.ApiResponse;
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
    private final StoreService storeService;



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
        Product createdProductWithStoreAndCategory = productService.createProductWithStoreAndCategory(product, storeId, categoryId);
        return new ResponseEntity<>(createdProductWithStoreAndCategory, HttpStatus.CREATED);
    }

    @GetMapping(params = "serial")
    public ResponseEntity<ApiResponse<ProductResource>> findBySerial(@RequestParam String serial) {
        final ProductResource productResource = getMapper().toResource(productService.findBySerial(serial));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductResource>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (!products.isEmpty()){
            List<ProductResource> productsResources = getMapper().toResources(products);
            return  new ResponseEntity<>(productsResources, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //find product by id
    @GetMapping(params = "id")
    public ResponseEntity<ProductResource> getProduct(@RequestParam Long id){
        final ProductResource productResource =getMapper().toResource(productService.get(id));
        return new ResponseEntity<>(productResource, HttpStatus.OK);
    }




    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


    //search product by name
    ///searchproducts?name=frappe
    @GetMapping("/searchproducts")
    public ResponseEntity<ProductResource> findProductByName(@RequestParam String name){
        Optional<Product> product = Optional.ofNullable(productService.findProductByName(name));
        if (product.isPresent()){
            ProductResource productResource = getMapper().toResource(productService.findByName(name));
            return new ResponseEntity<>(productResource, HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    //get products by store

    @GetMapping("/productsbystore/{storeId}")
    public ResponseEntity<List<ProductResource>> getProductsByStore(@PathVariable Long storeId){
        Optional<Store> store = Optional.ofNullable(storeService.get(storeId));
        if (store.isPresent()){
            List<Product> products =productService.getProductsByStore(store.get());
            List<ProductResource> productResources = getMapper().toResources(products);
            return new ResponseEntity<>(productResources, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}


