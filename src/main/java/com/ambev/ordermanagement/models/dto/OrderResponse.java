package com.ambev.ordermanagement.models.dto;

import com.ambev.ordermanagement.models.OrderStatus;
import com.ambev.ordermanagement.models.Product;
import lombok.Builder;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@RedisHash("order_response")
@Builder
public record OrderResponse(UUID id, double totalAmount, OrderStatus status, Set<Product> products) implements Serializable {
}
