package com.openclassrooms.nja.chatop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A Data Transfer Object (DTO) that represents a user.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class UserDTO {
    @NotNull(message = "Name cannot be empty") // Ensures name content is not null
    private String name; // User's name, required for certain operations

    @NotNull(message = "Email cannot be empty") // Validation to ensure the email is not null
    @Email(message = "Invalid email format") // Validation to ensure the email is in a correct format
    private String email; // User's email, must be valid
}
