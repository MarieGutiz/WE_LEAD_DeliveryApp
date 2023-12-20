package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.base.BaseComponent;
import gr.welead.spring.showcase.deliveryapp.model.BaseModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseRepositoryImpl<T extends BaseModel> extends BaseComponent implements BaseRepository<T, Long> {
    protected abstract ConcurrentHashMap<Long, T> getStorage();

    protected abstract AtomicLong getSequence();


    @Override
    public T create(final T item) {
        item.setId(getSequence().incrementAndGet());
        getStorage().put(item.getId(), item);
        return item;
    }


    @Override
    public List<T> createAll(final T... item) {
        return createAll(Arrays.asList(item));
    }

    @Override
    public List<T> createAll(final List<T> items) {
        List<T> updateItems = new ArrayList<>();
        items.forEach(
                item -> {
                    item.setId(getSequence().incrementAndGet());
                    getStorage().put(item.getId(), item);
                    updateItems.add(item);
                }
        );
        return updateItems;
    }

    @Override
    public void update(final T item) {
        getStorage().put(item.getId(), item);
    }

    @Override
    public void delete(final T item) {
        getStorage().remove(item.getId());
    }

    @Override
    public void deleteById(final Long id) {
        getStorage().remove(id);
    }

    @Override
    public T get(final Long id) {
        return getStorage().get(id);
    }

    @Override
    public boolean exists(final T item) {
        return getStorage().containsKey(item.getId());
    }

    @Override
    public List<T> findAll() {
        return getStorage().values().stream().toList();
    }

    @Override
    public Long count() {
        return (long) getStorage().values().size();
    }

}
