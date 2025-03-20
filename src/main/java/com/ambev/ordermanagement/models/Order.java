package com.ambev.ordermanagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="orders")
@Data
public class Order {
    @Id
    private UUID id;
    private double totalAmount;
    private OrderStatus status;

    @OneToMany(mappedBy = "product_id", fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private List<Product> products;

    public Order() {}

    public Order(List<Product> products, OrderStatus status) {
        this.products = products;
        this.totalAmount = totalAmount;
        this.status = status;
    }
}
