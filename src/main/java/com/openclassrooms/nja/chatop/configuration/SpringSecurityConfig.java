package com.openclassrooms.nja.chatop.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * This class is a configuration class for Spring Security.
 * It enables web security and method level security.
 * It also configures JWT authentication, CORS, and request authorization.
 * The class defines security filters, authentication managers, password encoders, and CORS configurations.
 */
@EnableWebSecurity // Enables Spring Web Security
@EnableMethodSecurity // Enables method level security
@Configuration // Indicates that this class is a configuration class
@OpenAPIDefinition(info = @Info(title = "ChâTop API", version = "V1")) // Defines OpenAPI information for Swagger UI
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
// Defines the security scheme for Swagger UI
public class SpringSecurityConfig {

    private final String[] WHITE_LIST = { // Array of endpoints that do not require authentication
            "/api/auth/register",
            "/api/auth/login",
            "/images/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
    @Autowired
    private JwtAuthFilter jwtAuthFilter; // Injects the JWT authentication filter
    @Value("${chatop.security.cors.origins}") // Injects CORS origins from application properties
    private String corsOrigins;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disables CSRF protection for simplicity
                .cors(Customizer.withDefaults()) // Applies default CORS configuration
                .authorizeHttpRequests(request -> request
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // Allows access to static resources
                        .requestMatchers(WHITE_LIST).permitAll() // Allows access to endpoints in the white list
                        .requestMatchers("/api/auth/me", "/api/rentals/**", "/api/messages/**").hasRole("USER") // Restricts access to certain endpoints to users with the "USER" role
                        .anyRequest().authenticated() // Requires authentication for any other request
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) // Defines the entry point for unauthorized requests
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)) // Sets the session management policy to stateless
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Adds the JWT authentication filter before the UsernamePasswordAuthenticationFilter
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Defines the password encoder to use for hashing passwords
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder); // Configures the authentication manager with user details service and password encoder
        return authManagerBuilder.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues(); // Applies default CORS configuration
        config.setAllowedOrigins(List.of(corsOrigins)); // Sets allowed origins
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Sets allowed methods
        config.setAllowedHeaders(List.of("*")); // Sets allowed headers
        config.setAllowCredentials(true); // Allows credentials

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Registers the CORS configuration for all paths
        return new CorsFilter(source);
    }
}
