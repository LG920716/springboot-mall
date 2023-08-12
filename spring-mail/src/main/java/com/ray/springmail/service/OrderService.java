package com.ray.springmail.service;

import com.ray.springmail.dto.OrderRequestDto;
import com.ray.springmail.entity.Order;

public interface OrderService {

    Integer createOrder(Integer userId, OrderRequestDto orderRequestDto);

    Order getOrder(Integer orderId);
}
