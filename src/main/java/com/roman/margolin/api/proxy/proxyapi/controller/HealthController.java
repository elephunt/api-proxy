package com.roman.margolin.api.proxy.proxyapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {


    @GetMapping("/health_check")
    public ResponseEntity health(){
        return  ResponseEntity.ok("Healthy");
    }


}
