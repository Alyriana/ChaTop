package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.request.RegisterDTO;
import com.openclassrooms.nja.chatop.dto.response.JwtAuthenticationDTO;
import com.openclassrooms.nja.chatop.exception.CreationFailureException;
import com.openclassrooms.nja.chatop.exception.UserAlreadyExistsException;
import com.openclassrooms.nja.chatop.service.JwtService;
import com.openclassrooms.nja.chatop.service.UserService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RegisterController {

    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        Authentication authentication;
        try {
            userService.createUser(registerDTO);
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            registerDTO.getEmail(),
                            registerDTO.getPassword()
                    )
            );
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        } catch (CreationFailureException e) {
            return new ResponseEntity<>("Registration Failed", HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(String.valueOf(authentication));
        if (token != null) {
            return ResponseEntity.ok(new JwtAuthenticationDTO(token));
        } else {
            return new ResponseEntity<>("Token Generation Failed", HttpStatus.UNAUTHORIZED);
        }
    }
}