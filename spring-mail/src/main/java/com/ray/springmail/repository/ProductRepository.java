package com.ray.springmail.repository;

import com.ray.springmail.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByProductId(int id);
}
