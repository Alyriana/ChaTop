package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.LoginDTO;
import com.openclassrooms.nja.chatop.dto.RegisterDTO;
import com.openclassrooms.nja.chatop.dto.UserConversionDTO;
import com.openclassrooms.nja.chatop.exception.AuthFailedException;
import com.openclassrooms.nja.chatop.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @Transactional
    public String registerAndGenerateToken(RegisterDTO registerDTO) {
        if (userService.existsByEmail(registerDTO.getEmail())) {
            throw new UserAlreadyExistsException("A user with this email already exists.");
        }
        UserConversionDTO userCreated = userService.createUser(registerDTO);
        return jwtService.generateToken(userCreated.getEmail());
    }

    @Transactional
    public String loginAndGenerateToken(LoginDTO loginDTO) {
        Authentication authentication = authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtService.generateToken(authentication.getName());
    }

    private Authentication authenticateUser(String email, String password) {
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (BadCredentialsException e) {
            throw new AuthFailedException("Invalid email or password.");
        }
    }
}
