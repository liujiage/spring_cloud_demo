package com.example.demo.controller;

import com.example.demo.DemoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private WebClient.Builder balancedClient;

    @Value("${spring.cloud.consul.discovery.service-name}")
    private String serverName;

    @Autowired
    private LoadBalancerClient balancerClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public String services(){
        return discoveryClient.getServices().toString();
    }

    @GetMapping("/call/elb")
    public Flux<String> callElb(){
        log.info("call elb");
        return balancedClient.build().get()
                .uri("http://spring-cloud-demo/elb")
                .retrieve().bodyToFlux(String.class);
    }


}
