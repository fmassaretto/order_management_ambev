package com.ambev.ordermanagement.services;

import com.ambev.ordermanagement.models.Order;
import com.ambev.ordermanagement.models.OrderStatus;
import com.ambev.ordermanagement.models.Product;
import com.ambev.ordermanagement.repositories.OrderRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.when;

class OrderServiceTest {

    @InjectMocks
    public OrderService orderService;

    @Mock
    public OrderRepo orderRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Product product1 = new Product("Product1", 100.00, 3);
        Product product2 = new Product("Product2", 300.00, 2);

        Order order = new Order(List.of(product1, product2), OrderStatus.PROCESSING);


        Order result = orderService.save(order);
        when(orderRepo.save(order)).thenReturn(order);

        Assertions.assertEquals(order, result);
    }

    @Test
    void findAll() {
    }
}