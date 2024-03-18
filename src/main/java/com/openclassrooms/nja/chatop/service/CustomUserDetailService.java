package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.entity.UsersEntity;
import com.openclassrooms.nja.chatop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * CustomUserDetailService is a class that implements the UserDetailsService interface,
 * providing custom user loading logic.
 */
@Service // Marks this class as a service component in the Spring context, providing business functionalities.
@RequiredArgsConstructor // Generates a constructor with required arguments (final fields) by Lombok.
public class CustomUserDetailService implements UserDetailsService {

    // Injects the UserRepository to access user data from the database.
    private final UserRepository userRepository;

    // Overrides the loadUserByUsername method to define custom user loading logic.
    @Override
    public UserDetails loadUserByUsername(final String userEmail) throws UsernameNotFoundException {
        // Attempts to find a user by email using the UserRepository.
        final Optional<UsersEntity> user = userRepository.findByEmail(userEmail);

        // If the user is not found, it throws a UsernameNotFoundException.
        if (user.isEmpty())
            throw new UsernameNotFoundException(userEmail + " not found");

        // If the user is found, it creates a new User (from Spring Security) with the user's details and authorities.
        // Here, every user is assigned a "ROLE_USER" authority by default.
        return new User(user.get().getEmail(), user.get().getPassword(),
                getGrantedAuthorities("ROLE_USER"));
    }

    // Helper method to create a list of GrantedAuthorities from a given role string.
    // This can be expanded to map multiple roles if needed.
    private List<GrantedAuthority> getGrantedAuthorities(final String role) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
