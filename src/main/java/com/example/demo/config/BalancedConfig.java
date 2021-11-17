package com.example.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BalancedConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder balancedClient(){
        return WebClient.builder();
    }

    @Bean
    public WebClient client(){
        return WebClient.builder().build();
    }
}
