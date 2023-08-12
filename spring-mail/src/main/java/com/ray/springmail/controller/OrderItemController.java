package com.ray.springmail.controller;

import com.ray.springmail.entity.OrderItem;
import com.ray.springmail.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItem(@PathVariable Integer orderId) {
        List<OrderItem> orderItems = orderItemService.getOrderItemById(orderId);
        return ResponseEntity.ok(orderItems);
    }
}
