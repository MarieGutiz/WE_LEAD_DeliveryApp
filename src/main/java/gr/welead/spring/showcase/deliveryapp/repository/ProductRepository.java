package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    Optional<Product> findByName(String name);
    List<Product> findByProductCategory(ProductCategory productCategory);

        //BaseRepository<Product, Long> {
//    Product findBySerial(final String serial);
//
//    List<Product> findByCategory(final ProductCategory productCategory);
//
//    public List<Product> findAllByIdIn(List<Long> ids);
}