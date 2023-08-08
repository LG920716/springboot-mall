package com.ray.springmail.service;

import com.ray.springmail.dto.ProductRequestDto;
import com.ray.springmail.entity.Product;

public interface ProductService {

    Product findById(int id);
    Product createProduct(ProductRequestDto productRequestDto);
    Product updateProduct(int id, ProductRequestDto productRequestDto);
    void deleteProductById(int id);
}
