package com.ray.springmail.dto;

import com.ray.springmail.constant.ProductCategory;
import lombok.Data;

@Data
public class ProductQueryParams {
    private String productName;
    private ProductCategory productCategory;
    private String orderBy;
    private String ascending;

    public ProductQueryParams(String productName, ProductCategory productCategory, String orderBy, String ascending) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.orderBy = orderBy;
        this.ascending = ascending;
    }
}
