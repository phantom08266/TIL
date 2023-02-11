package com.example.tdd_study.product.application.port;

import com.example.tdd_study.product.domain.Product;

public interface ProductPort {
    void save(Product product);

    Product getProduct(long productId);
}
