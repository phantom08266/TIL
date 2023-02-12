package com.example.tdd_study.order;

import com.example.tdd_study.product.adapter.ProductRepository;
import com.example.tdd_study.product.domain.DiscountPolicy;
import com.example.tdd_study.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class OrderServiceTest {
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        OrderRepository orderRepository = new OrderRepository();
        OrderPort orderPort = new OrderPort() {
            @Override
            public Product getProductById(Long productId) {
                return new Product("상품", 1000, DiscountPolicy.NONE);
            }

            @Override
            public void save(Order order) {
                orderRepository.save(order);
            }
        };
        orderService = new OrderService(orderPort);
    }

    @Test
    void 주문생성() {
        CreateOrderRequest request = new CreateOrderRequest(1L, 10);
        orderService.createOrder(request);
    }

    private class OrderService {
        private final OrderPort orderPort;

        private OrderService(OrderPort orderPort) {
            this.orderPort = orderPort;
        }

        public void createOrder(CreateOrderRequest request) {
            Product product = orderPort.getProductById(request.productId());

            Order order = new Order(product, request.quantity);
            orderPort.save(order);
        }

    }

    private class OrderPortAdapter implements OrderPort {
        private final ProductRepository productRepository;
        private final OrderRepository orderRepository;

        public OrderPortAdapter(ProductRepository productRepository, OrderRepository orderRepository) {
            this.productRepository = productRepository;
            this.orderRepository = orderRepository;
        }

        @Override
        public Product getProductById(Long productId) {
            return productRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        }

        @Override
        public void save(Order order) {
            orderRepository.save(order);
        }
    }

    public interface OrderPort {
        Product getProductById(Long productId);

        void save(OrderServiceTest.Order order);
    }

    private record CreateOrderRequest(Long productId, int quantity) {
        public CreateOrderRequest {
            Assert.notNull(productId, "상품 ID는 필수입니다.");
            Assert.isTrue(quantity > 0, "상품 수량은 1 이상이어야 합니다.");
        }
    }

    private class Order {
        private final Product product;
        private final int quantity;

        public Order(Product product, int quantity) {
            Assert.notNull(product, "상품은 필수입니다.");
            Assert.isTrue(quantity > 0, "상품 수량은 0 이상이어야 합니다.");
            this.product = product;
            this.quantity = quantity;
        }
    }

    private class OrderRepository {
        private final Map<Long, Order> persistence = new HashMap<>();
        private long id = 0;

        public void save(Order order) {
            updateId(id++);
            persistence.put(id, order);
        }

        private void updateId(long id) {
            this.id = id;
        }
    }
}
