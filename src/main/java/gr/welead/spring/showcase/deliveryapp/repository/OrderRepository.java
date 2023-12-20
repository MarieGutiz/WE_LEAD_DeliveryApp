package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Order;
import gr.welead.spring.showcase.deliveryapp.model.Store;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {
    List<Order> findByStore(Store store);

    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);//search for my last order

}
