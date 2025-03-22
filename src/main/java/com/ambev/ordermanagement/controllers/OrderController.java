package com.ambev.ordermanagement.controllers;

import com.ambev.ordermanagement.models.Order;
import com.ambev.ordermanagement.models.dto.OrderResponse;
import com.ambev.ordermanagement.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody Order order) {
        OrderResponse res = orderService.save(order);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<OrderResponse> res = orderService.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrderResponse> getUserById(@PathVariable("uuid") UUID uuid) {
        OrderResponse res = orderService.findById(uuid);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
