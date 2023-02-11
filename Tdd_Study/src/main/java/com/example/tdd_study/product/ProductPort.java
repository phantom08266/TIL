package com.example.tdd_study.product;

interface ProductPort {
    void save(Product product);

    Product getProduct(long productId);
}
