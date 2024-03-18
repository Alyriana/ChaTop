package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.RegisterDTO;
import com.openclassrooms.nja.chatop.dto.UserConversionDTO;
import com.openclassrooms.nja.chatop.entity.UsersEntity;
import com.openclassrooms.nja.chatop.exception.NotFoundException;
import com.openclassrooms.nja.chatop.exception.UnauthorizedException;
import com.openclassrooms.nja.chatop.exception.UserAlreadyExistsException;
import com.openclassrooms.nja.chatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * The UserService class provides methods for managing user-related operations.
 */
@Service // Marks this class as a service component in the Spring context, providing business functionalities.
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields) by Lombok.
public class UserService {

    private final UserRepository userRepository; // Dependency injection for UserRepository.
    private final PasswordEncoder passwordEncoder; // For encoding passwords before saving to the database.
    private final ConversionService conversionService; // For converting between entities and DTOs.

    // Retrieves a user by email and converts it to a DTO.
    @Transactional(readOnly = true)
    public UserConversionDTO findByEmail(String email) {
        return conversionService.userToDTO(userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with provided email does not exist.")));
    }

    // Retrieves a user by ID and converts it to a DTO.
    @Transactional(readOnly = true)
    public UserConversionDTO findById(Long id) {
        return conversionService.userToDTO(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with provided Id does not exist.")));
    }

    // Checks if a user exists by their email.
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Creates a new user from the RegisterDTO and returns the created user as a DTO.
    @Transactional
    public UserConversionDTO createUser(RegisterDTO registerDTO) {
        if (existsByEmail(registerDTO.getEmail())) {
            throw new UserAlreadyExistsException("A user with this email already exists.");
        }
        UsersEntity newUser = buildUserEntityFromDTO(registerDTO);
        return conversionService.userToDTO(userRepository.save(newUser));
    }

    // Helper method to build a UsersEntity object from a RegisterDTO.
    private UsersEntity buildUserEntityFromDTO(RegisterDTO registerDTO) {
        return UsersEntity.builder()
                .email(registerDTO.getEmail())
                .name(registerDTO.getName())
                .password(passwordEncoder.encode(registerDTO.getPassword())) // Encodes the password.
                .createdAt(Timestamp.from(Instant.now())) // Sets the current timestamp as the creation time.
                .build();
    }

    // Retrieves the username of the currently authenticated user.
    public String getCurrentAuthenticatedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User is not authenticated."); // Ensures the user is authenticated.
        }
        return authentication.getName(); // Returns the authenticated user's name.
    }
}
