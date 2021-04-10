package com.roman.margolin.api.proxy.proxyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProxyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApiApplication.class, args);
    }

}
