package com.ray.springmail.dto;

import com.ray.springmail.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotNull
    private String productName;
    @NotNull
    private ProductCategory category;
    @NotNull
    private String imageUrl;
    @NotNull
    private Integer price;
    @NotNull
    private Integer stock;

    private String description;

    public ProductRequestDto(){}
    public ProductRequestDto(@NotNull String productName, ProductCategory category, String imageUrl, Integer price, Integer stock, String description) {
        this.productName = productName;
        this.category = category;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }
}
