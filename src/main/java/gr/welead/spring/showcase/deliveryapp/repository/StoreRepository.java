package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Store;
import gr.welead.spring.showcase.deliveryapp.model.StoreCategory;

import java.util.List;

public interface StoreRepository extends BaseRepository<Store, Long> {

    List<Store> findByName(String name);

    List<Store> findByCategory(StoreCategory category);
}
