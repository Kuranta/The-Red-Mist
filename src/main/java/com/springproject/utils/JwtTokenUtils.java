package com.springproject.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration tokenLifetime;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> payload = new HashMap<>();
        List<String> roleList = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        payload.put("roles",roleList);

        Date startLifetime = new Date();
        Date endLifetime = new Date(startLifetime.getTime() + tokenLifetime.toMillis());

        return Jwts.builder()
                .setClaims(payload)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(startLifetime)
                .setExpiration(endLifetime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getEmail(String token){
        return getPayloadFromToken(token).getSubject();
    }

    public List<String> getRoles(String token){
        return getPayloadFromToken(token).get("roles", List.class);
    }

    private Claims getPayloadFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
