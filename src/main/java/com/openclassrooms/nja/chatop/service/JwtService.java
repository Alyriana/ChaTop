package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.exception.TokenErrorException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JwtService is a service component that provides JSON Web Token (JWT) functionalities.
 */
@Service // Marks this class as a service component in the Spring context, providing business functionalities.
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields) by Lombok.
public class JwtService {

    // Logger for logging errors and information.
    private final Logger logger = LoggerFactory.getLogger(JwtService.class);
    // Retrieves the JWT secret key from application properties.
    @Value("${jwt.secret.key}")
    private String secretKey;

    // Extracts the username (subject) from the JWT.
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extracts the expiration date from the JWT.
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extracts a specific claim from the JWT using a Claims resolver function.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extracts all claims from a JWT.
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey()) // Sets the signing key for validation.
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException ex) {
            logger.error("JWT validation error: {}", ex.getMessage());
            throw new TokenErrorException("JWT validation error: " + ex.getMessage());
        }
    }

    // Checks if the token has expired.
    private Boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    // Validates the JWT token.
    public final boolean validate(final String token) {
        Claims claims = extractAllClaims(token);
        return (claims != null && !isTokenExpired(token));
    }

    // Validates a token against a specific user's UserDetails.
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Generates a JWT token for a user.
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    // Helper method to create the token.
    private String createToken(Map<String, Object> claims, String userName) {
        long expirationTimeLong = 1000 * 60 * 15; // 15 min expiration time.
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

        // Builds the JWT with the specified claims, subject, issue date, expiration, and signature algorithm.
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    // Decodes the secret key and prepares it for signing the JWT.
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
