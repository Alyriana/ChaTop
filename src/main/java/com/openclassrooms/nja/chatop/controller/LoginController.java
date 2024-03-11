package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.request.LoginDTO;
import com.openclassrooms.nja.chatop.dto.response.JwtAuthenticationDTO;
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
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        String token = authenticationService.loginAndGenerateToken(loginDTO);
        return ResponseEntity.ok(new JwtAuthenticationDTO(token));
    }
}