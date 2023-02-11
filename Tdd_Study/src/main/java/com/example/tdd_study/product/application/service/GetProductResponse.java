package com.example.tdd_study.product.application.service;

import com.example.tdd_study.product.domain.DiscountPolicy;
import org.springframework.util.Assert;

public record GetProductResponse(long id, String name, int price, DiscountPolicy discountPolicy) {

    public GetProductResponse {
        Assert.notNull(id, "상품 ID는 필수입니다.");
        Assert.hasText(name, "상품명은 필수입니다.");
        Assert.notNull(discountPolicy, "할일 정책은 필수입니다.");
    }
}
