package com.ambev.ordermanagement.models.dto;

import com.ambev.ordermanagement.models.OrderStatus;
import com.ambev.ordermanagement.models.Product;

import java.util.List;

public record OrderRequest(double totalAmount, OrderStatus status, List<Product> products)  {
}
