package com.ambev.ordermanagement.controllers;

import com.ambev.ordermanagement.services.WelcomeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    private final WelcomeService welcomeService;

    @Value("${server.instance.id}")
    String instanceId;

    public WelcomeController(WelcomeService welcomeService) {
        this.welcomeService = welcomeService;
    }

    @GetMapping("/hello")
    public String hello() {
        return String.format("Hello from instance %s", instanceId);
    }

    @GetMapping("/")
    public String welcome() {
        return welcomeService.welcome();
    }
}
