package com.ambev.ordermanagement.services;

import com.ambev.ordermanagement.exceptions.DuplicateOrderException;
import com.ambev.ordermanagement.exceptions.OrderNotFoundException;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private OrderService orderService;

    private OrderResponse orderResponseCompleted;

    private Order order;
    private Order order2;
    private Order orderCompleted;
    private Order orderWithNullId;

    @BeforeEach
    void setUp() {
        Product product1 = new Product("Product1", 100.00, 3);
        Product product2 = new Product("Product2", 300.00, 2);
        Product product3 = new Product("Product3", 50.00, 2);
        Product product4 = new Product("Product4", 100.00, 5);

        Set<Product> setProducts1 = Set.of(product1, product2);
        Set<Product> setProducts2 = Set.of(product3, product4);

        UUID uuid = UUID.randomUUID();

        orderResponseCompleted = OrderResponse.builder()
                .id(uuid)
                .products(setProducts1)
                .totalAmount(900.00)
                .status(OrderStatus.COMPLETED)
                .build();

        order = new Order();
        order.setId(uuid);
        order.setTotalAmount(900.00);
        order.setStatus(OrderStatus.PROCESSING);
        order.setProducts(setProducts1);

        order2 = new Order();
        order2.setId(uuid);
        order2.setTotalAmount(900.00);
        order2.setStatus(OrderStatus.PROCESSING);
        order2.setProducts(setProducts2);

        orderCompleted = new Order();
        orderCompleted.setId(uuid);
        orderCompleted.setTotalAmount(900.00);
        orderCompleted.setStatus(OrderStatus.COMPLETED);
        orderCompleted.setProducts(setProducts1);

        orderWithNullId = new Order();
        orderWithNullId.setId(null);
        orderWithNullId.setTotalAmount(900.00);
        orderWithNullId.setStatus(OrderStatus.COMPLETED);
        orderWithNullId.setProducts(setProducts2);
    }

    @Test
    @DisplayName("When call save method with a valid order should return order saved test")
    void saveOrderTest() {
        given(orderRepo.save(order)).willReturn(order);

        OrderResponse result = orderService.save(order);

        assertEquals(orderResponseCompleted, result);
        assertEquals(orderResponseCompleted.totalAmount(), result.totalAmount());
        assertEquals(orderResponseCompleted.status(), result.status());
    }

    @Test
    @DisplayName("When call save method with order with null ID should return order saved test")
    void saveOrderIDNullTest() {
        given(orderRepo.save(orderWithNullId)).willReturn(orderCompleted);

        OrderResponse result = orderService.save(orderWithNullId);

        assertEquals(orderResponseCompleted, result);
        assertEquals(orderResponseCompleted.totalAmount(), result.totalAmount());
        assertEquals(orderResponseCompleted.status(), result.status());
    }

    @Test
    @DisplayName("When order already exists and call save method with a valid order should throw exception test")
    void saveOrderAlreadyExistsThrowsExceptionTest() {
        given(orderRepo.findById(any())).willReturn(Optional.of(order));

        assertThrows(DuplicateOrderException.class, () -> orderService.save(order));
    }

    @Test
    @DisplayName("When call findAll method should return list of orders test")
    void findAllOrdersTest() {
        List<Order> orders = List.of(order, order2);

        given(orderRepo.findAll()).willReturn(orders);

        List<OrderResponse> result = orderService.findAll();

        List<OrderResponse> expectedResult = orders.stream()
                .map(item -> OrderResponse.builder()
                        .id(item.getId())
                        .totalAmount(item.getTotalAmount())
                        .status(item.getStatus())
                        .products(item.getProducts()).build()).toList();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("When call findById method should return list of orders test")
    void findOrderByIdTest() {
        given(orderRepo.findById(any())).willReturn(Optional.of(order2));

        OrderResponse result = orderService.findById(any());

        OrderResponse expectedResult = OrderResponse.builder()
                .id(order2.getId())
                .totalAmount(order2.getTotalAmount())
                .status(order2.getStatus())
                .products(order2.getProducts()).build();

        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("When call findById method but cannot found order should throw exception test")
    void orderNotFoundTest() {
        given(orderRepo.findById(any())).willReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.findById(any()));
    }
}