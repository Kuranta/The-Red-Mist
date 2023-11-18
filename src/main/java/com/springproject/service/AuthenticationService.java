package com.springproject.service;

import com.springproject.dto.JwtRequest;
import com.springproject.dto.JwtResponse;
import com.springproject.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthenticationService {
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationService(JwtTokenUtils jwtTokenUtils, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }
        catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid username or password supplied.");
        }

        String token = jwtTokenUtils.generateToken(userDetailsService.loadUserByUsername(request.getEmail()));
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
