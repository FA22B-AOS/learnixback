package de.szut.learnixback.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;

@Service
public class KeycloakService {

    @Value("${keycloak.public-key}")
    private String publicKeyString;

    private Key publicKey;

    @PostConstruct
    public void init() {
        // Decode and create the public key
        byte[] decodedKey = Base64.getDecoder().decode(publicKeyString);
        this.publicKey = Keys.hmacShaKeyFor(decodedKey);
    }

    public String getUserIdFromToken(String token) {
        try {
            // Remove "Bearer " prefix if present
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);

            // Extract user ID (subject) from token
            return claims.getBody().getSubject();
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid token signature", e);
        } catch (Exception e) {
            throw new RuntimeException("Token parsing error", e);
        }
    }
}
