package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.vo.QuoteVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@Order(1)
public class Start1Service implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Start1Service.class);
    @Autowired
    private WebClient client;

    @Override
    public void run(String... args) throws Exception {
        log.info("call Start1Server run");
        Flux<QuoteVO> quote = client.get().uri("https://quoters.apps.pcfone.io/api/random").
                retrieve().bodyToFlux(QuoteVO.class);
        log.info("run1-"+quote.blockFirst(Duration.ofMinutes(10)));
     }
}
