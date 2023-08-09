package com.ray.springmail.specifications;

import com.ray.springmail.constant.ProductCategory;
import com.ray.springmail.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> productNameContains(String productName) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")), "%" + productName.toLowerCase() + "%");
            }
        };

    }
    public static Specification<Product> categoryEquals(ProductCategory category) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("category"), category);
            }
        };
    }
}
