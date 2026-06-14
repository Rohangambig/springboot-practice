package com.entire.demo.lib;

import com.entire.demo.dto.LoginRequestDTO;
import com.entire.demo.model.userModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import io.jsonwebtoken.Jwts;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtImpl {

    static SecretKey keys = Keys.hmacShaKeyFor("mySuperSecretKeyForJwtAuthentication123456".getBytes());

    public static String generateToken(userModel user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email",user.getEmail());
        claims.put("userId",user.getId());


        Date now = new Date();
        Date expireDate = new Date(now.getTime() + 3600 * 1000);

        return Jwts.builder()
                .claims(claims)
                .subject(user.getName())
                .issuedAt(now)
                .expiration(expireDate)
                .signWith(keys)
                .compact();
    }

    public static Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(keys)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
