package com.ambev.ordermanagement.repositories;

import com.ambev.ordermanagement.models.Order;
import com.ambev.ordermanagement.models.OrderStatus;
import com.ambev.ordermanagement.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.beans.Transient;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class OrderRepoTest {

    @Autowired
    private OrderRepo orderRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test 1:Save Employee Test")
    @Rollback(value = true)
    public void saveOrderTest() {
        Product product1 = new Product("Product1", 100.00, 3);
        Product product2 = new Product("Product2", 300.00, 2);

//        UUID uuid = UUID.randomUUID();

        Order order = new Order(Set.of(product1, product2), OrderStatus.PROCESSING);
//        order.setId(uuid);

        Order result = orderRepo.save(order);

        System.out.println(order);
        assertThat(order.getId()).isEqualTo(result.getId());
    }
}