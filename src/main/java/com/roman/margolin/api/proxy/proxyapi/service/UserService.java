package com.roman.margolin.api.proxy.proxyapi.service;

import com.roman.margolin.api.proxy.proxyapi.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserService {


    @LoadBalanced
    private final WebClient.Builder webClientBuilder;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<User> createUser(User user){
        String baseUrl = "lb://reader-manager/";
        Mono<User> userMono = webClientBuilder
                .build()
                .post()
                .uri(baseUrl).body(Mono.just(user), User.class)
                .retrieve()
                .bodyToMono(User.class);

        return userMono;
    }

 }
