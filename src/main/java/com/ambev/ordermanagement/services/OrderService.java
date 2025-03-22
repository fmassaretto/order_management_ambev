package com.ambev.ordermanagement.services;

import com.ambev.ordermanagement.exceptions.OrderNotFoundException;
import com.ambev.ordermanagement.models.Order;
import com.ambev.ordermanagement.models.dto.OrderResponse;
import com.ambev.ordermanagement.repositories.OrderRepo;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Transactional
    @CachePut(value = "orderResponse", key = "#order.id")
    public OrderResponse save(Order order) {

        Order res = orderRepo.save(order);

        return OrderResponse.builder()
                .id(res.getId())
                .totalAmount(res.getTotalAmount())
                .status(res.getStatus())
                .products(res.getProducts())
                .build();
    }

    @Cacheable(value = "orderResponse")
    public List<OrderResponse> findAll() {
        List<Order> res = orderRepo.findAll();

        return res.stream().map(order -> OrderResponse.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .products(order.getProducts())
                .build()).toList();

    }

    @Cacheable(value = "orderResponse", key = "#id")
    public OrderResponse findById(UUID id) {
        Optional<Order> res = orderRepo.findById(id);

        if (res.isEmpty()) {
            throw new OrderNotFoundException("Order not found");
        }

        return OrderResponse.builder()
                .id(res.get().getId())
                .totalAmount(res.get().getTotalAmount())
                .status(res.get().getStatus())
                .products(res.get().getProducts())
                .build();

    }
}
