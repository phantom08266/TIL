package com.example.tdd_study.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
class ProductController {

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
