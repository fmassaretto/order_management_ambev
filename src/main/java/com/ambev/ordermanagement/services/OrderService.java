package com.ambev.ordermanagement.services;

import com.ambev.ordermanagement.models.Order;
import com.ambev.ordermanagement.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public Order save(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> findAll() {
        return orderRepo.findAll();
    }
}
