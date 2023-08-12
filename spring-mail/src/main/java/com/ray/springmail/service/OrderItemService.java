package com.ray.springmail.service;

import com.ray.springmail.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> getOrderItemById(Integer orderId);
}
