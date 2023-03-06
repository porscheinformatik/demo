package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/secure").fullyAuthenticated()
                .anyRequest().permitAll();
        http.formLogin();
        http.logout().invalidateHttpSession(false);
        return http.build();
    }

    @GetMapping("/")
    public String index() {
        return "Hello, World!";
    }

    @GetMapping("/secure")
    public String secure() {
        return "Hello, secure " + SecurityContextHolder.getContext().getAuthentication().getPrincipal()  + "!";
    }
}
