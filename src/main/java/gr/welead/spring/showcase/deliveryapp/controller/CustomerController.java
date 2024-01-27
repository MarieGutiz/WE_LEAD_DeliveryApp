package gr.welead.spring.showcase.deliveryapp.controller;

import gr.welead.spring.showcase.deliveryapp.mapper.BaseMapper;
import gr.welead.spring.showcase.deliveryapp.mapper.CustomerMapper;
import gr.welead.spring.showcase.deliveryapp.model.Customer;
import gr.welead.spring.showcase.deliveryapp.service.BaseService;
import gr.welead.spring.showcase.deliveryapp.service.CustomerService;
import gr.welead.spring.showcase.deliveryapp.transfer.ApiResponse;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.CustomerResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    final CustomerService customerService;
    final CustomerMapper customerMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam String email) {
        Optional<Customer> customer = customerService.getCustomerByEmail(email);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomersByName(@RequestParam String firstName, @RequestParam String lastName) {
        Optional<Customer> customer = customerService.getCustomersByName(firstName, lastName);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerByPhoneNumber(@RequestParam String phoneNumber) {
        Optional<Customer> customer = customerService.getCustomerByPhoneNumber(phoneNumber);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody Customer updatedCustomer) {
        Customer result = customerService.updateCustomer(customerId, updatedCustomer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //
    @RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/with-royalty/{customerId}")
    public ResponseEntity<ApiResponse<CustomerResource>> getCustomerWithRoyaltyProgram(@PathVariable Long customerId) {
        Optional<Customer> customerOptional = customerService.findCustomerWithRoyaltyProgram(customerId);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            CustomerResource customerResource = customerMapper.toResource(customer);
            return ResponseEntity.ok(ApiResponse.<CustomerResource>builder().data(customerResource).build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
