package com.openclassrooms.nja.chatop.configuration;

import com.openclassrooms.nja.chatop.service.CustomUserDetailService;
import com.openclassrooms.nja.chatop.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This class is a Spring filter that handles JWT authentication for incoming requests.
 * It extracts the JWT token from the Authorization header, validates it, and sets up the security context with authentication if the token is valid.
 */
@Component // Marks this class as a Spring component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private final JwtService jwtService; // Injects the JWT service to handle token operations
    @Autowired
    private final CustomUserDetailService userService; // Injects the custom user service for user details retrieval

    // Constructor injection for JwtService and CustomUserDetailService with Lazy loading for userService
    @Autowired
    public JwtAuthFilter(JwtService jwtService, @Lazy CustomUserDetailService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    // The core method that executes the filter logic once per request
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization"); // Retrieves the Authorization header from the request
        String email = null;
        String jwtToken = null;

        // Checks if the Authorization header is present and begins with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7); // Extracts the JWT token from the header
            // Validates the token; if invalid, continues the filter chain without authentication
            if (!jwtService.validate(jwtToken)) {
                filterChain.doFilter(request, response);
                return;
            }
            email = jwtService.extractUsername(jwtToken); // Extracts the username (email) from the token
        }

        // If the email was successfully extracted and there's no authentication in the security context
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userService.loadUserByUsername(email); // Loads the user details
            // Validates the token with user details; if valid, sets up the security context with authentication
            if (jwtService.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Continues the filter chain
        filterChain.doFilter(request, response);
    }
}
