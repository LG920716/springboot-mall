package com.ray.springmail.controller;

import com.ray.springmail.dto.OrderRequestDto;
import com.ray.springmail.entity.Order;
import com.ray.springmail.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("users/{userId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid OrderRequestDto orderRequestDto) {
        Integer orderId = orderService.createOrder(userId, orderRequestDto);

        Order order = orderService.getOrder(orderId);

        return ResponseEntity.ok(order);
    }

    @GetMapping("users/orders/{orderId}")
    public ResponseEntity<Order> getOrderByOrderId(@PathVariable Integer orderId) {
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
