package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Offer;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.Store;
import gr.welead.spring.showcase.deliveryapp.model.StoreCategory;

import java.util.List;

public interface StoreService extends BaseService<Store, Long> {
    Store findByName(String name);

    List<Store> findByCategory(StoreCategory category);

    List<Store> findByCity(String city);


    Store create(Store store, StoreCategory storeCategory, String averageTime);

    List<Store> findMostFamousStores();

    List<Store> findMostFamousStoresPerCategory(String desiredCategory);

    void addReview(Long storeId, String review);

    boolean existsByMenuContaining(Product product);

    List<Product> getMenuByStoreId(Long storeId);

    Store findByOffersContains(Offer offer);

    Store findStoreWithReviews(String name);

}
