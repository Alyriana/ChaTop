package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.JwtAuthenticationDTO;
import com.openclassrooms.nja.chatop.dto.RegisterDTO;
import com.openclassrooms.nja.chatop.dto.ResponseDTO;
import com.openclassrooms.nja.chatop.exception.CreationFailureException;
import com.openclassrooms.nja.chatop.exception.UserAlreadyExistsException;
import com.openclassrooms.nja.chatop.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        try {
            String token = authenticationService.registerAndGenerateToken(registerDTO);
            return ResponseEntity.ok(new JwtAuthenticationDTO(token));
        } catch (UserAlreadyExistsException | CreationFailureException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .message(e.getMessage())
                            .errors(Collections.singletonList(e.getMessage()))
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .message("Registration failed due to an unexpected error.")
                            .errors(Collections.singletonList(e.getMessage()))
                            .build());
        }
    }
}
