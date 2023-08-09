package com.ray.springmail.service.Impl;

import com.ray.springmail.constant.ProductCategory;
import com.ray.springmail.dto.ProductRequestDto;
import com.ray.springmail.entity.Product;
import com.ray.springmail.repository.ProductRepository;
import com.ray.springmail.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

import static com.ray.springmail.specifications.ProductSpecifications.categoryEquals;
import static com.ray.springmail.specifications.ProductSpecifications.productNameContains;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts(String productName, ProductCategory productCategory) {
        if (productName != null && productCategory != null) {
            return productRepository.findAll(Specification.where(productNameContains(productName)).and(categoryEquals(productCategory)));
        } else if (productName != null) {
            return productRepository.findAll(Specification.where(productNameContains(productName)));
        } else if (productCategory != null) {
            return productRepository.findAll(Specification.where(categoryEquals(productCategory)));
        } else {
            return productRepository.findAll();
        }
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
