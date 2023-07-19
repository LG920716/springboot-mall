package com.ray.springmail.entity;

import com.ray.springmail.constant.ProductCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
@Data
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(name = "image_url")
    private String imageurl;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
}
