package com.ambev.ordermanagement.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Swagger2Config {
    @Bean
    public OpenAPI defineOpenApi() {
        Server server1 = new Server();
        server1.setUrl("http://localhost:9501");
        server1.setDescription("Development");

        Server server2 = new Server();
        server2.setUrl("http://localhost:9502");
        server2.setDescription("Development");

        Server server3 = new Server();
        server3.setUrl("http://localhost:9503");
        server3.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Fabio Massaretto");
        myContact.setEmail("fmassaretto@outlook.com");

        Info information = new Info()
                .title("Order Management System API")
                .version("1.0")
                .description("This API exposes endpoints to manage orders.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server1, server2, server3));
    }
}
