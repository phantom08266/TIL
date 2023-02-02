package com.example.tdd_study.product;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
class ProductRepository {

    private Long sequence = 0L;
    private Map<Long, Product> persistence = new HashMap<>();

    public void save(Product product) {
        product.assignId(++sequence);
        persistence.put(product.getId(), product);
    }
}
