package com.openclassrooms.nja.chatop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RegisterDTO represents the data transfer object for user registration.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class RegisterDTO {
    @NotNull(message = "Name cannot be empty") // Ensures name content is not null
    private String name; // User's name

    @NotNull(message = "Email cannot be empty") // Ensures email content is not null
    @Email(message = "Invalid email format") // Validation to ensure the email is in a correct format
    private String email; // User's email, must be a valid email format

    @NotNull(message = "Password cannot be empty") // Ensures password content is not null
    private String password; // User's password
}
