package com.romitshrivastava.products.repository;

import com.romitshrivastava.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {


    Optional<Product> findByProductName(String productName);
}
