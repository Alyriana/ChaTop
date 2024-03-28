package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.UserConversionDTO;
import com.openclassrooms.nja.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The UserController class is a Rest Controller that handles HTTP requests related to users.
 */
@RestController // Marks this class as a Rest Controller to handle HTTP requests
@RequiredArgsConstructor // Lombok's annotation to generate a constructor with required arguments for final fields
@RequestMapping("/api") // Sets the base path for all requests handled by this controller
public class UserController {

    private final UserService userService; // Injects the UserService to use its methods

    // Documents the endpoint for getting the currently authenticated user's details
    @Operation(summary = "User details", description = "Return details of authenticated user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Details of the user logged in.",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "401", description = "Invalid user. UNAUTHORIZED",
                            content = @Content)
            })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/auth/me") // Maps HTTP GET requests to /api/auth/me to this method
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        // Uses the Authentication object to get the current user's email and find their details
        return ResponseEntity.ok(userService.findByEmail(authentication.getName()));
    }

    // Documents the endpoint for getting a user's details by their ID
    @Operation(summary = "User by id", description = "Return details of user by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Details of the user by id.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserConversionDTO.class))}),
                    @ApiResponse(responseCode = "401", description = "Invalid user. UNAUTHORIZED",
                            content = @Content)
            })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/user/{id}") // Maps HTTP GET requests to /api/user/{id} to this method
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        // Calls the userService to find a user by their ID and return their details
        return ResponseEntity.ok(userService.findById(id));
    }
}
