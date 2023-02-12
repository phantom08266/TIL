package com.example.tdd_study.product.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void 상품수정() {
        Product product = new Product("상품명", 1000, DiscountPolicy.NONE);
        product.update("변경", 2000, DiscountPolicy.NONE);

        assertThat(product.getPrice()).isEqualTo(2000);
    }
}