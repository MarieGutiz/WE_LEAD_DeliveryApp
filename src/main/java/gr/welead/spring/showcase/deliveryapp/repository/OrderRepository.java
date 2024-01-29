package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Customer;
import gr.welead.spring.showcase.deliveryapp.model.Order;
import gr.welead.spring.showcase.deliveryapp.model.OrderStatus;
import gr.welead.spring.showcase.deliveryapp.model.Store;
import gr.welead.spring.showcase.deliveryapp.transfer.OrderDetailsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStore(Store store);

    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);//search for my last order

    @Modifying
    @Query("UPDATE Order o SET o.orderStatus = :status WHERE o.id = :orderId")
    void updateOrderStatus(@Param("orderId") Long orderId, @Param("status") OrderStatus status);

    List<Order> findByCustomer(Customer customer);

    @Query("SELECT o.id AS order_id, " +
            "o.orderDate AS orderDate, " +
            "o.subTotal AS subTotal, " +
            "o.total AS total, " +
            "o.orderStatus AS orderStatus, " +
            "o.paymentMethod AS paymentMethod, " +
            "c.id AS customer_id, " +
            "a.firstName AS customer_firstName, " +
            "a.lastName AS customer_lastName, " +
            "s.id AS store_id, " +
            "s.name AS store_name, " +
            "oi.id AS orderItem_id, " +
            "oi.price AS orderItem_price, " +
            "oi.quantity AS orderItem_quantity, " +
            "p.id AS product_id, " +
            "p.name AS product_name " +
            "FROM Order o " +
            "JOIN o.customer c " +
            "JOIN c.account a " +
            "JOIN o.store s " +
            "LEFT JOIN o.orderItems oi " +
            "LEFT JOIN oi.product p " +
            "WHERE c.id = :customerId")
    List<OrderDetailsReport> findOrdersDetailsByCustomerId(@Param("customerId") Long customerId);

//    @Query(value = "${}", nativeQuery = true)
//    List<OrderDetailsReport[]> findOrdersDetailsByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT o.id AS order_id, " +
            "o.orderDate AS orderDate, " +
            "o.subTotal AS subTotal, " +
            "o.total AS total, " +
            "o.orderStatus AS orderStatus, " +
            "o.paymentMethod AS paymentMethod, " +
            "c.id AS customer_id, " +
            "a.firstName AS customer_firstName, " +
            "a.lastName AS customer_lastName, " +
            "s.id AS store_id, " +
            "s.name AS store_name, " +
            "oi.id AS orderItem_id, " +
            "oi.price AS orderItem_price, " +
            "oi.quantity AS orderItem_quantity, " +
            "p.id AS product_id, " +
            "p.name AS product_name " +
            "FROM Order o " +
            "JOIN o.customer c " +
            "JOIN c.account a " +
            "JOIN o.store s " +
            "LEFT JOIN o.orderItems oi " +
            "LEFT JOIN oi.product p ")
    List<OrderDetailsReport> findOrdersDetailsByAll();
}
