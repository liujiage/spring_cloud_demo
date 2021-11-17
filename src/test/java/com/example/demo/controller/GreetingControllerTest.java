package com.example.demo.controller;

import com.example.demo.service.HiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GreetingControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private GreetingController controller;

    @Test
    public void contentLoads() throws Exception{
        assertThat(controller).isNotNull();
    }

    @Test
    public void helloTest() throws Exception{
        assertThat(this.restTemplate.
                getForObject("http://localhost:"+this.port+"/hello",
                        String.class)).contains("Hello");
    }

    @Test
    public void hiTest() throws Exception{
        assertThat(this.restTemplate.
                getForObject("http://localhost:"+this.port+"/hi",
                        String.class)).contains("hi");
    }

}
