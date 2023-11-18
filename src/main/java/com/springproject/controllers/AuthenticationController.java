package com.springproject.controllers;


import com.springproject.dto.JwtRequest;
import com.springproject.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping( "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest){
        return authenticationService.createAuthenticationToken(jwtRequest);
    }
}
