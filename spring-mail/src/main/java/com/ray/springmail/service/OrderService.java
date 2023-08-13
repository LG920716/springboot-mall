package com.ray.springmail.service;

import com.ray.springmail.dto.OrderRequestDto;
import com.ray.springmail.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Integer createOrder(Integer userId, OrderRequestDto orderRequestDto);
    Order getOrder(Integer orderId);
    Page<Order> getOrderListByUserId(Integer userId, Pageable pageable);
}
