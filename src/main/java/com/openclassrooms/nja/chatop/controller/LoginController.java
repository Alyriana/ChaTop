package com.openclassrooms.nja.chatop.controller;

import com.openclassrooms.nja.chatop.dto.request.LoginDTO;
import com.openclassrooms.nja.chatop.dto.response.JwtAuthenticationDTO;
import com.openclassrooms.nja.chatop.entity.UsersEntity;
import com.openclassrooms.nja.chatop.service.JwtService;
import com.openclassrooms.nja.chatop.service.UserService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Data
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        Optional<UsersEntity> user = Optional.ofNullable(userService.findByEmail(loginDTO.getEmail()));
        if (user.isEmpty()) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        if (passwordEncoder.matches(loginDTO.getPassword(), user.orElseThrow().getPassword())) {
            String token = jwtService.generateToken(user.orElseThrow().getEmail());
            if (token != null) {
                return ResponseEntity.ok(new JwtAuthenticationDTO(token));
            } else {
                return new ResponseEntity<>("Token Generation Failed", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Bad Credentials", HttpStatus.BAD_REQUEST);
        }
    }
}