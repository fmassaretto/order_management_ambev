package com.ambev.ordermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OrdermanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdermanagementApplication.class, args);
	}

}
