package com.openclassrooms.nja.chatop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginDTO is a data transfer object that represents the user's login credentials.
 * It contains fields for email and password.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Builder // Provides the builder pattern for object creation
@AllArgsConstructor // Generates a constructor with arguments for all fields
@NoArgsConstructor // Generates a no-argument constructor
public class LoginDTO {

    @NotNull(message = "Email cannot be empty") // Validation to ensure the email is not null
    @Email(message = "Invalid email format") // Validation to ensure the email is in a correct format
    private String email;

    @NotNull(message = "Password cannot be empty") // Validation to ensure the password is not null
    private String password;
}
