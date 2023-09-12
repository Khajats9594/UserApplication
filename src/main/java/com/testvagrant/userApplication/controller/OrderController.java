package com.testvagrant.userApplication.controller;

import com.testvagrant.userApplication.dto.APIResponse;
import com.testvagrant.userApplication.dto.OrderRequest;
import com.testvagrant.userApplication.dto.OrderResponse;
import com.testvagrant.userApplication.dto.UpdateCustomerDetails;
import com.testvagrant.userApplication.entity.Customer;
import com.testvagrant.userApplication.services.CustomerService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class OrderController {
    @Autowired
    private CustomerService customerService;



    @PostMapping("/placeOrder")
    public ResponseEntity<APIResponse<Object>> placeOrder(@RequestBody OrderRequest request) {

        Customer customer = customerService.saveCustomer(request);

        APIResponse<Object> response = APIResponse.builder()
                .status("SUCCESS")
                .results(customer)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse<Object>> getAllCustomer() {

        List<Customer> allCustomer = customerService.findAllCustomer();

        APIResponse<Object> response = APIResponse.builder()
                .status("SUCCESS")
                .results(allCustomer)
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getAllProductsOfCustomer")
    public ResponseEntity<APIResponse<Object>> getAllProductsNameOfCustomer() {

        List<OrderResponse> allProductOfCustomer = customerService.getAllProductOfCustomer();

        APIResponse<Object> response = APIResponse.builder()
                .status("SUCCESS")
                .results(allProductOfCustomer)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<APIResponse<Object>> getAllProductsNameOfCustomer(@PathVariable Integer id) {

        Customer customer = customerService.getCustomerById(id);

        APIResponse<Object> response = APIResponse.builder()
                .status("SUCCESS")
                .results(customer)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<Object>> updateCustomer(
            @PathVariable @Min(value = 1, message = "User ID must be a positive number") Integer id,
            @RequestBody UpdateCustomerDetails details) {

        Customer customer = customerService.updateCustomer(id, details);

        APIResponse<Object> response = APIResponse.builder()
                .status("SUCCESS")
                .results(customer)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<Object>> deleteCustomer(@PathVariable Integer id){

        String message = customerService.deleteCustomer(id);

        APIResponse<Object> response = APIResponse.builder()
                .status("SUCCESS")
                .results(message)
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
