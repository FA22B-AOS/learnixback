package de.szut.learnixback.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final HttpServletRequest request;

    public String getCurrentUserId() {
        try {
            // Get the Authorization header from the request
            String authorizationHeader = request.getHeader("Authorization");

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                // Extract the token
                String token = authorizationHeader.substring(7);

                // Decode the JWT token
                DecodedJWT decodedJWT = JWT.decode(token);

                // Extract the user ID from the JWT claims (assuming it contains a 'sub' claim)
                return decodedJWT.getSubject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if authorization header is missing or an exception occurred
    }
}
