package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.dto.request.RegisterDTO;
import com.openclassrooms.nja.chatop.entity.UsersEntity;
import com.openclassrooms.nja.chatop.exception.CreationFailureException;
import com.openclassrooms.nja.chatop.exception.NotFoundException;
import com.openclassrooms.nja.chatop.exception.UserAlreadyExistsException;
import com.openclassrooms.nja.chatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public UsersEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));
    }

    @Transactional
    public UsersEntity createUser(RegisterDTO user) {
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
            return userRepository.save(userCreated);
        } catch (Exception e) {
            throw new CreationFailureException("Creation failed. " + e);
        }
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UsersEntity> user = userRepository.findByEmail(email);
        return user.map(usersEntity -> new User(email, usersEntity.getPassword(), new ArrayList<>()))
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
    }
}