package com.example.tdd_study.product;

import com.example.tdd_study.ApiTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductApiTest extends ApiTest {

    @Autowired
    private ProductService productService;

    @Test
    void 상품등록() {
        final AddProductRequest request = 상품등록_생성요청();

        // API요청
        ExtractableResponse<Response> response = 상품등록_요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ExtractableResponse<Response> 상품등록_요청(AddProductRequest request) {
        return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/products")
                .then()
                .log().all().extract();
    }

    private AddProductRequest 상품등록_생성요청() {
        final String name = "상품명";
        final int price = 1000;
        final DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        final AddProductRequest request = new AddProductRequest(name, price, discountPolicy);
        return request;
    }


}
