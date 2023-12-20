package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Order;
import gr.welead.spring.showcase.deliveryapp.model.Store;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order> implements OrderRepository {
    private final ConcurrentHashMap<Long, Order> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, Order> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {
        return sequence;
    }

    @Override
    public List<Order> findByStore(Store store) {
        return getStorage().values()
                .stream()
                .filter(order -> order.getStore().equals(store))
                .toList();
    }

    @Override
    public List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return getStorage().values()
                .stream()
                .filter(order ->
                        order
                                .getOrderDate().isAfter(startDate)
                                && order.getOrderDate().isBefore(endDate))
                .toList();
    }
}
