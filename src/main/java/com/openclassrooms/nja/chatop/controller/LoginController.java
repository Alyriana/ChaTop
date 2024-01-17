package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.request.LoginDTO;
import com.openclassrooms.nja.chatop.dto.response.JwtAuthenticationDTO;
import com.openclassrooms.nja.chatop.service.JWTService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) {

        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getPassword()
                    )
            );
        } catch (Exception e) {
            return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken(authentication);

        if (jwt != null) {
            return ResponseEntity.ok(new JwtAuthenticationDTO(jwt));
        } else {
            return new ResponseEntity<>("Token Generation Failed", HttpStatus.UNAUTHORIZED);
        }
    }
}