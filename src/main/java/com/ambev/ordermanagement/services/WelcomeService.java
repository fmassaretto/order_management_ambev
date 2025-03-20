package com.ambev.ordermanagement.services;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

    public String welcome() {
        return "Welcome!";
    }
}
