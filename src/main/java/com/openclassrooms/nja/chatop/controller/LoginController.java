package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.JwtAuthenticationDTO;
import com.openclassrooms.nja.chatop.dto.LoginDTO;
import com.openclassrooms.nja.chatop.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The LoginController class is responsible for handling login-related API requests.
 * It provides an API endpoint for authenticating a user with email and password,
 * and returns a JSON Web Token (JWT) upon successful authentication.
 */
@RequiredArgsConstructor
// Indicates that this class is required to have constructor-based dependency injection for its fields
@RestController // Marks this class as a controller where every method returns a domain object instead of a view
@RequestMapping("/api/auth") // Maps all requests starting with "/api/auth" to this controller
public class LoginController {

    private final AuthenticationService authenticationService; // Injects the AuthenticationService to use its methods

    // Annotated method to handle HTTP POST requests matched with "/api/auth/login"
    // Summarizes the operation in the OpenAPI documentation
    @Operation(summary = "Authenticate user", description = "Authenticate user by email and password, and return a JWT.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User logged in.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = JwtAuthenticationDTO.class))}),
                    @ApiResponse(responseCode = "401", description = "Invalid email or password. UNAUTHORIZED",
                            content = @Content)
            })
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        // Authenticates the user and generates a JWT, then returns it in the response body
        return ResponseEntity.ok(new JwtAuthenticationDTO(authenticationService.loginAndGenerateToken(loginDTO)));
    }
}
