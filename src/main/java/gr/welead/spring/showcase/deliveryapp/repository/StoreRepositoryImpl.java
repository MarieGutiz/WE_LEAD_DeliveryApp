package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Store;
import gr.welead.spring.showcase.deliveryapp.model.StoreCategory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class StoreRepositoryImpl extends BaseRepositoryImpl<Store> implements StoreRepository {
    @Override
    protected ConcurrentHashMap<Long, Store> getStorage() {
        return null;
    }

    @Override
    protected AtomicLong getSequence() {
        return null;
    }

    @Override
    public List<Store> findByName(String name) {
        return getStorage().values()
                .stream()
                .filter(store -> store.getName().equalsIgnoreCase(name))
                .toList();
    }

    @Override
    public List<Store> findByCategory(StoreCategory category) {
        return getStorage().values()
                .stream()
                .filter(store -> store.getCategory() == category)
                .toList();
    }
}
