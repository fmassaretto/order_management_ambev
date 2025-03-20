package com.ambev.ordermanagement.controllers;

import com.ambev.ordermanagement.services.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    private WelcomeService welcomeService;

    @GetMapping("/")
    public String welcome() {
        return welcomeService.welcome();
    }
}
