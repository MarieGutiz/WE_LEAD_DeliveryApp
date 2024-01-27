package gr.welead.spring.showcase.deliveryapp.controller;

import gr.welead.spring.showcase.deliveryapp.mapper.BaseMapper;
import gr.welead.spring.showcase.deliveryapp.mapper.OrderMapper;
import gr.welead.spring.showcase.deliveryapp.model.Customer;
import gr.welead.spring.showcase.deliveryapp.model.Order;
import gr.welead.spring.showcase.deliveryapp.service.BaseService;
import gr.welead.spring.showcase.deliveryapp.service.CustomerService;
import gr.welead.spring.showcase.deliveryapp.service.OrderService;
import gr.welead.spring.showcase.deliveryapp.transfer.ApiResponse;
import gr.welead.spring.showcase.deliveryapp.transfer.OrderDetailsReport;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.OrderResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController extends BaseController<Order, OrderResource> {
    final OrderService orderService;
    final OrderMapper orderMapper;
    final CustomerService customerService;

    @Override
    protected BaseService<Order, Long> getBaseService() {
        return orderService;
    }

    @Override
    protected BaseMapper<Order, OrderResource> getMapper() {
        return orderMapper;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderDetailsReport[]>>> getOrdersByCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.findById(customerId);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        List<OrderDetailsReport[]> orders = orderService.findOrdersDetailsByCustomerId(customer.getId());
//        logger.info("orders {} ", orders);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderDetailsReport[]>>builder()
                        .data(orders)
                        .build()
        );
    }

    @GetMapping("/details/all")
    public ResponseEntity<ApiResponse<List<OrderDetailsReport[]>>> getAllOrderDetails() {
        List<OrderDetailsReport[]> orderDetailsList = orderService.findOrdersDetailsByAll();

        logger.info("orders {} ", orderDetailsList);
        return ResponseEntity.ok(
                ApiResponse.<List<OrderDetailsReport[]>>builder()
                        .data(orderDetailsList)
                        .build()
        );
    }
}

