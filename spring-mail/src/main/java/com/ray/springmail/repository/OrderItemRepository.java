package com.ray.springmail.repository;

import com.ray.springmail.entity.Order;
import com.ray.springmail.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
