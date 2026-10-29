package com.parkgrid.auth.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        /*
         * ---------------------------------------------------------
         * 1. Authentication endpoints do NOT need JWT.
         * ---------------------------------------------------------
         *
         * These endpoints must be accessible before the user
         * receives a JWT.
         */
        String path = request.getServletPath();

        if (path.equals("/api/auth/login")
                || path.equals("/api/auth/register")
                || path.equals("/api/auth/verify-otp")) {

            filterChain.doFilter(request, response);
            return;
        }

        /*
         * ---------------------------------------------------------
         * 2. Read Authorization header.
         * ---------------------------------------------------------
         */
        String authorizationHeader =
                request.getHeader("Authorization");

        /*
         * If there is no Authorization header,
         * simply continue the request.
         *
         * SecurityConfig will decide whether the endpoint
         * requires authentication.
         */
        if (authorizationHeader == null
                || !authorizationHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        /*
         * ---------------------------------------------------------
         * 3. Extract JWT.
         * ---------------------------------------------------------
         */
        String token =
                authorizationHeader.substring(7);

        try {

            /*
             * -----------------------------------------------------
             * 4. Validate JWT.
             * -----------------------------------------------------
             */
            if (jwtService.isValid(token)) {

                /*
                 * -------------------------------------------------
                 * 5. Extract user information from JWT.
                 * -------------------------------------------------
                 */
                String email =
                        jwtService.extractEmail(token);

                String role =
                        jwtService.extractRole(token);

                /*
                 * -------------------------------------------------
                 * 6. Create Spring Security authorities.
                 *
                 * ROLE_USER
                 * ROLE_ADMIN
                 * -------------------------------------------------
                 */
                SimpleGrantedAuthority authority =
                        new SimpleGrantedAuthority(
                                "ROLE_" + role);

                /*
                 * -------------------------------------------------
                 * 7. Create authenticated user.
                 * -------------------------------------------------
                 */
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                java.util.List.of(authority));

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                /*
                 * -------------------------------------------------
                 * 8. Put authentication into SecurityContext.
                 * -------------------------------------------------
                 */
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }

        } catch (Exception e) {

            /*
             * Invalid JWT:
             *
             * We do NOT manually return 403 here.
             * We simply continue without authentication.
             *
             * Spring Security will then reject protected
             * endpoints appropriately.
             */
            SecurityContextHolder.clearContext();
        }

        /*
         * Continue request.
         */
        filterChain.doFilter(request, response);
    }
}
