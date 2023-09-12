package com.testvagrant.userApplication.services;

import com.testvagrant.userApplication.dto.OrderRequest;
import com.testvagrant.userApplication.dto.OrderResponse;
import com.testvagrant.userApplication.dto.UpdateCustomerDetails;
import com.testvagrant.userApplication.entity.Customer;
import com.testvagrant.userApplication.exception.CustomerNotFoundException;
import com.testvagrant.userApplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(OrderRequest request){
        return customerRepository.save(request.getCustomer());
    }

    public List<Customer> findAllCustomer(){

        return customerRepository.findAll();
    }

    public List<OrderResponse> getAllProductOfCustomer(){

        return customerRepository.getALlProductsOfCustomer();
    }

    public Customer getCustomerById(Integer id){

        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("customer for this id " + id + " not found"));
    }
    public Customer updateCustomer(Integer id, UpdateCustomerDetails details){

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("customer for this id " + id + " not found"));
        customer.setName(details.getName());
        customer.setEmail(details.getEmail());
        return customerRepository.save(customer);
    }

    public String deleteCustomer(Integer id){
        customerRepository.deleteById(id);
        return "Customer with ID " + id + " has been successfully deleted.";
    }
}
