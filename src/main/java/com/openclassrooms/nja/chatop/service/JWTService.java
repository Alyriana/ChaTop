package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.exception.TokenGenerationException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
@Service
@RequiredArgsConstructor
public class JWTService {

    private final JwtEncoder jwtEncoder;
    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);

    public String generateToken(Authentication authentication) {
        try {
            Instant now = Instant.now();
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self")
                    .issuedAt(now)
                    .expiresAt(now.plus(1, ChronoUnit.DAYS))
                    .subject(authentication.getName())
                    .build();
            JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
            return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        } catch (JwtEncodingException e) {
            logger.error("Error generating JWT: {}", e.getMessage(), e);
            throw new TokenGenerationException("Error generating JWT: " + e.getMessage());
        }
    }
}