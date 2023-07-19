package com.ray.springmail.service.Impl;

import com.ray.springmail.entity.Product;
import com.ray.springmail.repository.ProductRepository;
import com.ray.springmail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(int id) {
        return productRepository.findByProductId(id);
    }
}
