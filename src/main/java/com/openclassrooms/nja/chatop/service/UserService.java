package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.RegisterDTO;
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

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UsersEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with provided email does not exist."));
    }

    @Transactional(readOnly = true)
    public UsersEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with provided Id does not exist."));
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public UsersEntity createUser(RegisterDTO registerDTO) {
        if (existsByEmail(registerDTO.getEmail())) {
            throw new UserAlreadyExistsException("A user with this email already exists.");
        }
        UsersEntity newUser = buildUserEntityFromDTO(registerDTO);
        return userRepository.save(newUser);
    }

    private UsersEntity buildUserEntityFromDTO(RegisterDTO registerDTO) {
        return UsersEntity.builder()
                .email(registerDTO.getEmail())
                .name(registerDTO.getName())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public String getCurrentAuthenticatedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnauthorizedException("User is not authenticated.");
        }
        return authentication.getName();
    }
}
