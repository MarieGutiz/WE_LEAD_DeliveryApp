package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Store;
import gr.welead.spring.showcase.deliveryapp.model.StoreCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends BaseRepository<Store, Long> {

    List<Store> findByName(String name);

    List<Store> findByCategory(StoreCategory category);

    List<Store> findByCity(String city);

    void addReview(Long storeId, String review);

}
