package com.ray.springmail.service.Impl;

import com.ray.springmail.dto.BuyItem;
import com.ray.springmail.dto.OrderRequestDto;
import com.ray.springmail.entity.Order;
import com.ray.springmail.entity.OrderItem;
import com.ray.springmail.entity.Product;
import com.ray.springmail.entity.User;
import com.ray.springmail.repository.OrderItemRepository;
import com.ray.springmail.repository.OrderRepository;
import com.ray.springmail.repository.ProductRepository;
import com.ray.springmail.repository.UserRepository;
import com.ray.springmail.service.OrderItemService;
import com.ray.springmail.service.OrderService;
import com.ray.springmail.service.ProductService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, OrderRequestDto orderRequestDto) {

        // 檢查user是否存在
        User user = userRepository.getUserByUserId(userId);
        if (user == null) {
            log.warn("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 檢查商品是否存在和庫存是否足夠和計算訂單總金額
        int totalAmount = 0;
        for (BuyItem buyItem : orderRequestDto.getBuyItemList()) {
            Product product = productRepository.findByProductId(buyItem.getProductId());
            if (product == null) {
                log.warn("商品 id:{} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存不足，無法購買，剩餘庫存 {}，欲購買庫存 {}", product.getProductName(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else {
                productService.updateStock(buyItem.getProductId(), buyItem.getQuantity());
                int amount = buyItem.getQuantity() * product.getPrice();
                totalAmount += amount;
            }
        }

        // 新增訂單
        Order order = new Order();
        order.setUser(userRepository.getUserByUserId(userId));
        order.setTotalAmount(totalAmount);
        order.setCreatedDate(ZonedDateTime.now());
        order.setLastModifiedDate(ZonedDateTime.now());

        Order savedOrder = orderRepository.save(order);

        // 新增訂單項目
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
            List<OrderItem> orderItems = orderItemService.getOrderItemById(orderId);
            order.setOrderItems(orderItems);
        }
        return order;
    }

}
