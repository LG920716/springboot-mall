package com.ray.springmail.service;

import com.ray.springmail.constant.ProductCategory;
import com.ray.springmail.dto.ProductRequestDto;
import com.ray.springmail.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(String productName, ProductCategory productCategory);
    Product findById(int id);
    Product createProduct(ProductRequestDto productRequestDto);
    Product updateProduct(int id, ProductRequestDto productRequestDto);
    void deleteProductById(int id);
}
