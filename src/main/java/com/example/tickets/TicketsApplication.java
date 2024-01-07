package com.example.tickets;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.tickets.model"})

public class TicketsApplication {
    
    @GetMapping("/")
    public String home() {
        return "Bienvenido a la aplicación de Tickets";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(TicketsApplication.class, args);
    }
}
