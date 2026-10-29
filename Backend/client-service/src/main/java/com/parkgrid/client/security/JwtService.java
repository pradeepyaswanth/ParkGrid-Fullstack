package com.parkgrid.client.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET =
            "ParkGridSecretKeyForJwtAuthentication2026VerySecureKey";

    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes(StandardCharsets.UTF_8)
            );


    public Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String extractRole(String token) {

        return extractAllClaims(token)
                .get("role", String.class);
    }


    public Long extractUserId(String token) {

        Number userId =
                extractAllClaims(token)
                        .get("userId", Number.class);

        return userId != null
                ? userId.longValue()
                : null;
    }


    public String extractEmail(String token) {

        return extractAllClaims(token)
                .getSubject();
    }


    public boolean isValid(String token) {

        try {

            extractAllClaims(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}