package com.testvagrant.userApplication.repository;

import com.testvagrant.userApplication.dto.OrderResponse;
import com.testvagrant.userApplication.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query("SELECT new com.testvagrant.userApplication.dto.OrderResponse(c.name, p.productName) FROM Customer c JOIN c.products p")
    public List<OrderResponse> getALlProductsOfCustomer();
}
