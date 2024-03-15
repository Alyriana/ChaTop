package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.JwtAuthenticationDTO;
import com.openclassrooms.nja.chatop.dto.RegisterDTO;
import com.openclassrooms.nja.chatop.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(new JwtAuthenticationDTO(authenticationService.registerAndGenerateToken(registerDTO)));
    }
}
