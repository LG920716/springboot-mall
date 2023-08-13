package com.ray.springmail.service;

import com.ray.springmail.dto.ProductQueryParams;
import com.ray.springmail.dto.ProductRequestDto;
import com.ray.springmail.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getProducts(ProductQueryParams productQueryParams, Pageable pageable);
    Product findById(int id);
    Product createProduct(ProductRequestDto productRequestDto);
    Product updateProduct(int id, ProductRequestDto productRequestDto);
    void updateStock(Integer productId, Integer quantity);
    void deleteProductById(int id);
}
