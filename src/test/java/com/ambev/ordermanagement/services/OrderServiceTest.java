package com.ambev.ordermanagement.services;

import com.ambev.ordermanagement.models.Order;
import com.ambev.ordermanagement.models.OrderStatus;
import com.ambev.ordermanagement.models.Product;
import com.ambev.ordermanagement.repositories.OrderRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private OrderService orderService;

    private Order order;


    @BeforeEach
    void setUp() {
        Product product1 = new Product("Product1", 100.00, 3);
        Product product2 = new Product("Product2", 300.00, 2);

        order = new Order(Set.of(product1, product2), OrderStatus.PROCESSING);
    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    @DisplayName("When call save method with a valid order should return order saved test")
    void saveOrderTest() {
        given(orderRepo.save(order)).willReturn(order);

        Order result = orderService.save(order);

        assertEquals(order, result);
    }

    @Test
    void findAll() {
    }
}