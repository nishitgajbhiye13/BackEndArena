package com.BackEndArena.bea.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final Key key ;

    private final long Expiration_TIME;

    public JwtUtil(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration}") long expirationTime
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.Expiration_TIME = expirationTime;
    }

    //create sign JWT

    public String generateToken(String subject, Map<String,Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Expiration_TIME))
                .signWith(key)
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(token).getBody();

    }

    public String extractUserName(String token){
        return extractClaims(token).getSubject();
    }
}
