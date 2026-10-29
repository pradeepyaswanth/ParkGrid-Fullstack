package com.parkgrid.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.parkgrid.client.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;


    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http

            // Disable CSRF because we use JWT
            .csrf(csrf -> csrf.disable())


            // No server-side sessions
            .sessionManagement(session ->
                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    )
            )


            // Authorization rules
            .authorizeHttpRequests(auth -> auth

                    // Allow browser preflight
                    .requestMatchers(
                            HttpMethod.OPTIONS,
                            "/**"
                    ).permitAll()


                    // Health check
                    .requestMatchers(
                            "/actuator/health"
                    ).permitAll()


                    // Client APIs require USER
                    .requestMatchers(
                            "/api/client/**"
                    ).hasRole("USER")


                    // Everything else requires authentication
                    .anyRequest().authenticated()
            )


            // Add our JWT filter
            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );


        return http.build();
    }
}