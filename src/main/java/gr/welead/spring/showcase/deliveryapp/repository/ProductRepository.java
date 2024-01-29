package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import gr.welead.spring.showcase.deliveryapp.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findBySerial(final String serial);

    Product findByName(final String name);

    Optional<Product> findById(Long id);

    List<Product> findByCategory(final ProductCategory productCategory);

    public List<Product> findAllByIdIn(List<Long> ids);
    public List<Product> findByStore(Store store);


}
