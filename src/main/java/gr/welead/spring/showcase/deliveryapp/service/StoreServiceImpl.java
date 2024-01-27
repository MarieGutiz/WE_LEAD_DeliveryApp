package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.*;
import gr.welead.spring.showcase.deliveryapp.repository.OrderRepository;
import gr.welead.spring.showcase.deliveryapp.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {
    final StoreRepository storeRepository;
    final OrderRepository orderRepository;

    @Override
    protected JpaRepository<Store, Long> getRepository() {
        return storeRepository;
    }


    //Search for the desired store, either by name or by category.
    @Override
    public Store findByName(final String name) {
        return storeRepository.findByName(name);
    }

    @Override
    public List<Store> findByCategory(final StoreCategory category) {
        return storeRepository.findByCategory(category);
    }

    @Override
    public List<Store> findByCity(String city) {
        return storeRepository.findByAddress_City(city);
    }

    @Override
    public void addReview(Long storeId, String comment) {
//        Store store = new Store();
//        store.setId(storeId);
//
//        StoreReview review = StoreReview.builder()
//                .comment(comment)
//                .store(store)
//                .build();
//        store.setReviews(review);

    }

    @Override
    public boolean existsByMenuContaining(Product product) {
        return storeRepository.existsByMenuContains(product);
    }

    @Override
    public List<Product> getMenuByStoreId(Long storeId) {
        return storeRepository.findMenuByStoreId(storeId);
    }

    @Override
    public Store findByOffersContains(Offer offer) {
        return storeRepository.findByOffersContains(offer);
    }

    @Override
    public Store findStoreWithReviews(String name) {
        return storeRepository.findStoreWithReviews(name);
    }

    @Override
    public Store create(final Store store, final StoreCategory storeCategory, String averageTime) {
        store.setCategory(StoreCategory.valueOf(storeCategory.getCategory()));
//        store.setTime(getAverageObj(averageTime));

        return storeRepository.save(store);
    }

    private AverageTime getAverageObj(String averageTime) {
        AverageTime build = AverageTime.builder().build();
        build.setAverageTime(averageTime);
        return build;
    }

    //List of the most famous stores in general and per category.
    @Override
    public List<Store> findMostFamousStores() {//TO DO
        //Most famous stores based in orders (The store that sell the most is somehow the most famous) and ranking
        List<Store> allStores = storeRepository.findAll();
        return calculateTotalOrderAndCompare(allStores);
    }

    @Override
    public List<Store> findMostFamousStoresPerCategory(String category) {//TO DO
        List<Store> allStores = storeRepository.findAll();
        // Convert the string category to the corresponding enum value
        StoreCategory desiredCategory;
        try {
            desiredCategory = StoreCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Handle the case where the provided category is not valid
            throw new IllegalArgumentException("Invalid category: " + category);
        }
        //Filter all stores by category first
        List<Store> filteredStoresByCategory = allStores.stream()
                .filter(store -> store.getCategory() != null)
                .filter(store -> store.getCategory().equals(desiredCategory))
                .toList();
        logger.info("Category selected {} ", desiredCategory.getCategory());
        logger.info("Filtered Stores by category {} ", filteredStoresByCategory);
        return calculateTotalOrderAndCompare(filteredStoresByCategory);
    }

    private List<Store> calculateTotalOrderAndCompare(List<Store> stores) {
        List<Order> allOrdersIssued = orderRepository.findAll();
        //Calculate total Orders for each store

        // Create a new mutable list
        List<Store> mutableStores = new ArrayList<>(stores);

        // Calculate total Orders for each store
        Map<Store, Long> ordersByStore = allOrdersIssued.stream()
                .filter(order -> stores.contains(order.getStore()))
                .collect(Collectors.groupingBy(Order::getStore, Collectors.counting()));

        // Filter out stores with zero orders
        mutableStores = mutableStores.stream()
                .filter(store -> ordersByStore.containsKey(store) && ordersByStore.get(store) > 0)
                .collect(Collectors.toList());

        // Sort stores based on ranking and total Orders
        mutableStores.sort(Comparator.comparing(Store::getRanking)
                .thenComparing(store -> ordersByStore.getOrDefault(store, 0L))
                .reversed());

        return mutableStores;
    }
}
