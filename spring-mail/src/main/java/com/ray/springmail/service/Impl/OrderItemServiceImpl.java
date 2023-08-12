package com.ray.springmail.service.Impl;

import com.ray.springmail.entity.OrderItem;
import com.ray.springmail.repository.OrderItemRepository;
import com.ray.springmail.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public List<OrderItem> getOrderItemById(Integer orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItem> allOrderItem = orderItemRepository.findAll();
        for (OrderItem orderItem : allOrderItem) {
            if (orderItem.getOrder().getOrderId().equals(orderId)) {
                orderItems.add(orderItem);
            }
        }
        return orderItems;
    }
}
