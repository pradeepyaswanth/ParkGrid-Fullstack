package com.parkgrid.auth.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.parkgrid.auth.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    /*
     * IMPORTANT:
     * The same secret must be used wherever we validate this JWT.
     */
    private static final String SECRET =
            "ParkGridSecretKeyForJwtAuthentication2026VerySecureKey";

    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes(StandardCharsets.UTF_8));

    /*
     * Generate JWT after successful login.
     */
    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())

                // Store user's role inside JWT
                .claim("role", user.getRole().name())

                .issuedAt(new Date())

                // Token valid for 24 hours
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 86400000))

                .signWith(key)
                .compact();
    }

    /*
     * Extract email from JWT.
     */
    public String extractEmail(String token) {

        return getClaims(token).getSubject();
    }

    /*
     * Extract role from JWT.
     */
    public String extractRole(String token) {

        return getClaims(token)
                .get("role", String.class);
    }

    /*
     * Check whether JWT is valid.
     */
    public boolean isValid(String token) {

        try {
            getClaims(token);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /*
     * Parse and verify JWT.
     */
    private Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
