package com.example.demo.service;

import com.example.demo.vo.QuoteVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@Order(2)
public class Start2Service implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Start2Service.class);
    @Autowired
    private WebClient client;

    @Override
    public void run(String... args) throws Exception {
        log.info("call Start2Server run");
        Mono<QuoteVO> quote = client.get().uri("https://quoters.apps.pcfone.io/api/random").
                retrieve().bodyToMono(QuoteVO.class);
        //log.info("run2-"+quote.block(Duration.ofMinutes(1)));
    }
}
