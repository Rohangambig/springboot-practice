package com.entire.demo.utili;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    String SECRET = "prKEpjfFnVWTs2yv0rMNB5E3J5ZacLOM8EZ2HPyAAkI=";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email) {
        Date now = new Date();
        return Jwts.builder()
                .subject(email)
                .signWith(key)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + 3600 * 1000))
                .compact();
    }

    public boolean checkExpiration(Date expireDate) {
        return expireDate.after(new Date());
    }

    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
