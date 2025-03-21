package com.ambev.ordermanagement.services;

import com.ambev.ordermanagement.models.Order;
import com.ambev.ordermanagement.models.OrderStatus;
import com.ambev.ordermanagement.models.Product;
import com.ambev.ordermanagement.models.dto.OrderResponse;
import com.ambev.ordermanagement.repositories.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private OrderService orderService;

    private OrderResponse orderResponse;

    private Order order;

    @BeforeEach
    void setUp() {
        Product product1 = new Product("Product1", 100.00, 3);
        Product product2 = new Product("Product2", 300.00, 2);

        Set<Product> products = Set.of(product1, product2);

        UUID uuid = UUID.randomUUID();

        orderResponse = OrderResponse.builder()
                .id(uuid)
                .products(products)
                .totalAmount(123.12)
                .status(OrderStatus.PROCESSING)
                .build();

        order = new Order();
        order.setId(uuid);
        order.setTotalAmount(123.12);
        order.setStatus(OrderStatus.PROCESSING);
        order.setProducts(products);
    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    @DisplayName("When call save method with a valid order should return order saved test")
    void saveOrderTest() {
        given(orderRepo.save(order)).willReturn(order);

        OrderResponse result = orderService.save(order);

        assertEquals(orderResponse, result);
    }

    @Test
    void findAll() {
    }
}