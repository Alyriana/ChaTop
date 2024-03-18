package com.openclassrooms.nja.chatop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JwtAuthenticationDTO is a data transfer object that represents a JWT authentication token.
 * It contains a String field called "token" to store the JWT token.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class JwtAuthenticationDTO {
    private String token; // Field to store JWT token
}
