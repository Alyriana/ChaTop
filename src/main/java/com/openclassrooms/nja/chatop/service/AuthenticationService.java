package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.LoginDTO;
import com.openclassrooms.nja.chatop.dto.RegisterDTO;
import com.openclassrooms.nja.chatop.dto.UserConversionDTO;
import com.openclassrooms.nja.chatop.exception.AuthFailedException;
import com.openclassrooms.nja.chatop.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AuthenticationService is a service component that provides authentication functionalities.
 */
@Service // Marks this class as a service component in the Spring context, providing business functionalities.
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields) by Lombok.
public class AuthenticationService {

    private final AuthenticationManager authenticationManager; // Manages authentication processes.
    private final JwtService jwtService; // Service for JWT operations, like generating tokens.
    private final UserService userService; // User service for handling user data operations.

    @Transactional // Indicates that this method should be executed within a transaction.
    public String registerAndGenerateToken(RegisterDTO registerDTO) {
        // Checks if a user already exists with the given email. Throws exception if true.
        if (userService.existsByEmail(registerDTO.getEmail())) {
            throw new UserAlreadyExistsException("A user with this email already exists.");
        }
        // Creates a new user with the provided registration details.
        UserConversionDTO userCreated = userService.createUser(registerDTO);
        // Generates a JWT token for the newly created user.
        return jwtService.generateToken(userCreated.getEmail());
    }

    @Transactional // Ensures this method is transactional, maintaining data consistency.
    public String loginAndGenerateToken(LoginDTO loginDTO) {
        // Authenticates the user with provided credentials. Throws exception if authentication fails.
        Authentication authentication = authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());
        // Sets the authentication in the security context, marking the user as authenticated.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generates and returns a JWT token for the authenticated user.
        return jwtService.generateToken(authentication.getName());
    }

    // Helper method to authenticate a user with the authentication manager.
    private Authentication authenticateUser(String email, String password) {
        try {
            // Attempts to authenticate the user with provided credentials.
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (BadCredentialsException e) {
            // Throws a custom exception if authentication fails due to bad credentials.
            throw new AuthFailedException("Invalid email or password.");
        }
    }
}
