package com.ray.springmail.service.Impl;

import com.ray.springmail.dto.ProductQueryParams;
import com.ray.springmail.dto.ProductRequestDto;
import com.ray.springmail.entity.Product;
import com.ray.springmail.repository.ProductRepository;
import com.ray.springmail.service.ProductService;
import com.ray.springmail.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> getProducts(ProductQueryParams productQueryParams, Pageable pageable) {
        return productRepository.findAll(ProductSpecifications.toSpecification(productQueryParams), pageable);
//        return productPage.getContent();
    }


    @Override
    public Product findById(int id) {
        return productRepository.findByProductId(id);
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setProductName(productRequestDto.getProductName());
        product.setCategory(productRequestDto.getCategory());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());
        product.setDescription(productRequestDto.getDescription());
        product.setCreatedDate(ZonedDateTime.now());
        product.setLastModifiedDate(ZonedDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findByProductId(id);
        product.setProductName(productRequestDto.getProductName());
        product.setCategory(productRequestDto.getCategory());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());
        product.setDescription(productRequestDto.getDescription());
        product.setLastModifiedDate(ZonedDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }
}
