package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.JwtAuthenticationDTO;
import com.openclassrooms.nja.chatop.dto.RegisterDTO;
import com.openclassrooms.nja.chatop.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The RegisterController class handles the registration of a user by email and password and returns a JWT.
 * It exposes the "/api/auth/register" POST endpoint for user registration.
 */
@RequiredArgsConstructor
// Marks this class for constructor injection, requiring final fields to be initialized through the constructor
@RestController // Indicates that this class will be a REST controller
@RequestMapping("/api/auth") // Maps HTTP requests starting with "/api/auth" to methods in this controller
public class RegisterController {

    private final AuthenticationService authenticationService; // Injects the AuthenticationService to use its methods

    // Documents the register endpoint for the OpenAPI specification
    @Operation(summary = "Register user", description = "Register user by email and password, and return a JWT.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User saved and logged in.",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "400", description = "Name, password, or email cannot be empty. BAD_REQUEST",
                            content = @Content),
                    @ApiResponse(responseCode = "409", description = "A user with this email already exists. CONFLICT",
                            content = @Content)
            })
    @PostMapping("/register") // Maps POST requests to "/api/auth/register" to this method
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        // Validates the RegisterDTO object, registers the user, generates a JWT, and returns it
        return ResponseEntity.ok(new JwtAuthenticationDTO(authenticationService.registerAndGenerateToken(registerDTO)));
    }
}
