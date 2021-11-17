package com.example.demo.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.service.HiService;
import com.example.demo.vo.GreetingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @Value("${server.port}")
    private int port;

    @Autowired
    private HiService hiService;

    @GetMapping("/greeting")
    public GreetingVO greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new GreetingVO(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/hi")
    public String hi() {
         return hiService.say();
    }

    @GetMapping("/elb")
    public String elb(){
        return "this is server"+port;
    }


}
