package com.roman.margolin.api.proxy.proxyapi.controller;

import com.roman.margolin.api.proxy.proxyapi.model.User;
import com.roman.margolin.api.proxy.proxyapi.service.KeyCloackService;
import com.roman.margolin.api.proxy.proxyapi.service.UserService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    private final KeyCloackService keyCloackService;
    private final UserService userService;
    public UserController(KeyCloackService keyCloackService, UserService userService) {
        this.keyCloackService = keyCloackService;
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<Mono<User>> createUser(@RequestBody User user){
        UserRepresentation userRepresentation = this.keyCloackService.addUser(user);
        user.setId(userRepresentation.getId());
        Mono<User> user1 = this.userService.createUser(user);
        return ResponseEntity.ok(user1);
    }
}
