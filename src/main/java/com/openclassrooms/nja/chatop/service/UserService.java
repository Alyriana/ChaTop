package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.request.RegisterDTO;
import com.openclassrooms.nja.chatop.entity.UsersEntity;
import com.openclassrooms.nja.chatop.exception.CreationFailureException;
import com.openclassrooms.nja.chatop.exception.NotFoundException;
import com.openclassrooms.nja.chatop.exception.UserAlreadyExistsException;
import com.openclassrooms.nja.chatop.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UsersEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
    }

    @Transactional
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public UsersEntity findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));
    }

    @Transactional
    public void createUser(RegisterDTO user) {
        try {
            if (existsByEmail(user.getEmail())) {
                throw new UserAlreadyExistsException("User already exists with this email");
            }
            UsersEntity userCreated = UsersEntity.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            userRepository.save(userCreated);
        } catch (DataIntegrityViolationException e) {
            throw new CreationFailureException("User creation failed due to data integrity violation: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new CreationFailureException("User creation failed due to invalid data: " + e.getMessage());
        }
    }
}