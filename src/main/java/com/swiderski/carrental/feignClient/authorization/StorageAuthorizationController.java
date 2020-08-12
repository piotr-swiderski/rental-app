package com.swiderski.carrental.feignClient.authorization;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@EnableFeignClients(basePackageClasses = AuthorizationClient.class)
public class StorageAuthorizationController {

    private final AuthorizationClient client;

    public StorageAuthorizationController(AuthorizationClient authorizationClient) {
        this.client = authorizationClient;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return client.authenticateUser(loginRequest);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return client.registerUser(signUpRequest);
    }
}
