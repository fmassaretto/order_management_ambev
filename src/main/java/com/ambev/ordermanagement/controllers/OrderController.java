package com.ambev.ordermanagement.controllers;

import com.ambev.ordermanagement.models.Order;
import com.ambev.ordermanagement.models.dto.OrderResponse;
import com.ambev.ordermanagement.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public OrderResponse save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping
    public List<OrderResponse> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{uuid}")
    public OrderResponse getUserById(@PathVariable("uuid") UUID uuid) {
        return orderService.findById(uuid);
    }
}
