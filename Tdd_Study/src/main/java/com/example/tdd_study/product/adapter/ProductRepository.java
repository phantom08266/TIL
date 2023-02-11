package com.example.tdd_study.product.adapter;

import com.example.tdd_study.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
