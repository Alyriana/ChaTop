package com.openclassrooms.nja.chatop.service;

import com.openclassrooms.nja.chatop.entity.UsersEntity;
import com.openclassrooms.nja.chatop.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

@Data
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public final UserDetails loadUserByUsername(final String userEmail) throws UsernameNotFoundException {
        final Optional<UsersEntity> user = userRepository.findByEmail(userEmail);

        if (user.isEmpty())
            throw new UsernameNotFoundException(userEmail + " not found");
        return new User(user.get().getEmail(), user.get().getPassword(),
                getGrantedAuthorities("ROLE_USER"));
    }

    private List<GrantedAuthority> getGrantedAuthorities(final String role) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}