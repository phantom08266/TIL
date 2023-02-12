package com.example.tdd_study.product;

import com.example.tdd_study.product.application.service.GetProductResponse;
import com.example.tdd_study.product.application.service.ProductService;
import com.example.tdd_study.product.application.service.UpdateProductRequest;
import com.example.tdd_study.product.domain.DiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품조회() {
        //상품 등록
        productService.addProduct(ProductSteps.상품등록_생성요청());
        final long productId = 1;
        //상품을 조회
        final GetProductResponse response = productService.getProduct(productId);

        //상품의 응답을 검증
        assertThat(response).isNotNull();
    }

    @Test
    void 상품수정() {
        final Long productId = 1L;
        final UpdateProductRequest request = new UpdateProductRequest("상품수정", 2000, DiscountPolicy.NONE);

        productService.updateProduct(productId, request);
    }

}
