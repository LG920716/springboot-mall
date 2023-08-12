package com.ray.springmail.repository;

import com.ray.springmail.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByOrderId(Integer orderId);
}
