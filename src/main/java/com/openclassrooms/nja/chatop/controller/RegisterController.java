package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.request.RegisterDTO;
import com.openclassrooms.nja.chatop.dto.response.JwtAuthenticationDTO;
import com.openclassrooms.nja.chatop.dto.response.ResponseDTO;
import com.openclassrooms.nja.chatop.exception.CreationFailureException;
import com.openclassrooms.nja.chatop.exception.UserAlreadyExistsException;
import com.openclassrooms.nja.chatop.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Registration failed due to an unexpected error."));
        }
    }
}
