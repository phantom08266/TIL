package com.example.tdd_study.product.application.service;


import com.example.tdd_study.product.application.port.ProductPort;
import com.example.tdd_study.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductPort productPort;

    ProductService(final ProductPort productPort) {
        this.productPort = productPort;
    }

    @Transactional
    public void addProduct(AddProductRequest request) {
        Product product = new Product(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);
    }

    public GetProductResponse getProduct(long productId) {
        final Product product = productPort.getProduct(productId);
        return new GetProductResponse(product.getId(), product.getName(), product.getPrice(), product.getDiscountPolicy());
    }

    public void updateProduct(Long productId, UpdateProductRequest request) {
        Product product = productPort.getProduct(productId);

        product.update(request.name(), request.price(), request.discountPolicy());
        productPort.save(product);
    }
}
