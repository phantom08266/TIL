package com.example.tdd_study.product;

import com.example.tdd_study.product.application.service.AddProductRequest;
import com.example.tdd_study.product.application.service.GetProductResponse;
import com.example.tdd_study.product.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody AddProductRequest addProductRequest) {
        productService.addProduct(addProductRequest);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public GetProductResponse getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }
}
