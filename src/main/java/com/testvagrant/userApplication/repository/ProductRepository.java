package com.testvagrant.userApplication.repository;

import com.testvagrant.userApplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
