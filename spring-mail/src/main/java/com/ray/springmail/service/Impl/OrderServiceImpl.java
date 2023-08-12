package com.ray.springmail.service.Impl;

import com.ray.springmail.controller.OrderItemController;
import com.ray.springmail.dto.BuyItem;
import com.ray.springmail.dto.OrderRequestDto;
import com.ray.springmail.entity.Order;
import com.ray.springmail.entity.OrderItem;
import com.ray.springmail.entity.Product;
import com.ray.springmail.repository.OrderItemRepository;
import com.ray.springmail.repository.OrderRepository;
import com.ray.springmail.repository.ProductRepository;
import com.ray.springmail.repository.UserRepository;
import com.ray.springmail.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemController orderItemController;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, OrderRequestDto orderRequestDto) {
        int totalAmount = 0;
        for (BuyItem buyItem : orderRequestDto.getBuyItemList()){
            Product product = productRepository.findByProductId(buyItem.getProductId());
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;
        }

        Order order = new Order();
        order.setUser(userRepository.getUserByUserId(userId));
        order.setTotalAmount(totalAmount);
        order.setCreatedDate(ZonedDateTime.now());
        order.setLastModifiedDate(ZonedDateTime.now());

        Order savedOrder = orderRepository.save(order);

        for (BuyItem buyItem : orderRequestDto.getBuyItemList()) {
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.findByProductId(buyItem.getProductId());
            orderItem.setOrder(savedOrder);  // 將創建的 order 實例設置給 OrderItem 的 Order 屬性
            orderItem.setProduct(product);
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setSubtotal(buyItem.getQuantity() * product.getPrice());
            orderItemRepository.save(orderItem);
        }

        return savedOrder.getOrderId();
    }

    @Override
    public Order getOrder(Integer orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        if (order != null) {
            ResponseEntity<List<OrderItem>> responseEntity = orderItemController.getOrderItem(orderId);
            List<OrderItem> orderItems = responseEntity.getBody();
            order.setOrderItems(orderItems);
        }
        return order;
    }

}
