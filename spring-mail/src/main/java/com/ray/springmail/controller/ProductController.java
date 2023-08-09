package com.ray.springmail.controller;

import com.ray.springmail.constant.ProductCategory;
import com.ray.springmail.dto.ProductRequestDto;
import com.ray.springmail.entity.Product;
import com.ray.springmail.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) ProductCategory category
    ) {
        List<Product> products = productService.getProducts(productName, category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        Product product = productService.findById(id);
        if(product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return  ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
        Product product = productService.createProduct(productRequestDto);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequestDto productRequestDto){
        if(productService.findById(productId) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Product product = productService.updateProduct(productId, productRequestDto);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer productId){
        if(productService.findById(productId) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productService.deleteProductById(productId);
        return ResponseEntity.ok().build();

    }
}
