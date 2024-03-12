package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.ResponseDTO;
import com.openclassrooms.nja.chatop.entity.UsersEntity;
import com.openclassrooms.nja.chatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/auth/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        Optional<UsersEntity> user = userRepository.findByEmail(authentication.getName());
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("User not found")
                            .errors(Collections.singletonList("No user associated with the email provided"))
                            .build());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<UsersEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("User not found")
                            .errors(Collections.singletonList("No user found with the provided ID"))
                            .build());
        }
    }
}