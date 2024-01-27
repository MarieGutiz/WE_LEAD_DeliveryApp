package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Offer;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.Store;
import gr.welead.spring.showcase.deliveryapp.model.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByName(String name);

    List<Store> findByCategory(StoreCategory category);

    List<Store> findByAddress_City(String city);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Store s WHERE :product MEMBER OF s.menu")
    boolean existsByMenuContains(@Param("product") Product product);

    @Query("SELECT s.menu FROM Store s WHERE s.id = :storeId")
    List<Product> findMenuByStoreId(@Param("storeId") Long storeId);

    Store findByOffersContains(Offer offer);

    //Fetch store by name, category and city

    @Query("SELECT s FROM Store s LEFT JOIN FETCH s.reviews WHERE s.name = :name")
    Store findStoreWithReviews(@Param("name") String name);

    @Query("SELECT s FROM Store s LEFT JOIN FETCH s.reviews WHERE s.category = :category")
    List<Store> findByCategoryWithReviews(@Param("category") StoreCategory category);

    @Query("SELECT s FROM Store s LEFT JOIN FETCH s.reviews WHERE s.address.city = :city")
    List<Store> findByCityWithReviews(@Param("city") String city);


}
