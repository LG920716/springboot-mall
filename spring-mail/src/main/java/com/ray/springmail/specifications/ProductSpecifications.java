package com.ray.springmail.specifications;

import com.ray.springmail.constant.ProductCategory;
import com.ray.springmail.dto.ProductQueryParams;
import com.ray.springmail.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public interface ProductSpecifications {

    public static Specification<Product> toSpecification(ProductQueryParams productQueryParams) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (productQueryParams.getProductName() != null) {
                predicates.add(productNameContains(productQueryParams.getProductName()).toPredicate(root, query, criteriaBuilder));
            }

            if (productQueryParams.getProductCategory() != null) {
                predicates.add(categoryEquals(productQueryParams.getProductCategory()).toPredicate(root, query, criteriaBuilder));
            }

            Predicate[] p = new Predicate[predicates.size()];
            query.where(criteriaBuilder.and(predicates.toArray(p)));
            if (productQueryParams.getAscending().equals("DESC")) {
                query.orderBy(criteriaBuilder.desc(root.get(productQueryParams.getOrderBy())));
            } else {
                query.orderBy(criteriaBuilder.asc(root.get(productQueryParams.getOrderBy())));
            }
            return query.getRestriction();
        };
    }

    public static Specification<Product> productNameContains(String productName) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")), "%" + productName.toLowerCase() + "%");
        };
    }

    public static Specification<Product> categoryEquals(ProductCategory category) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("category"), category);
        };
    }
}
