package com.openclassrooms.nja.chatop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * This class represents a Response Data Transfer Object (DTO) used to encapsulate the response data
 * returned from an API endpoint.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class ResponseDTO {
    private HttpStatus status; // HTTP status of the response
    private String message; // Custom message for the response
    private List<String> errors; // List of error messages, if any
}
