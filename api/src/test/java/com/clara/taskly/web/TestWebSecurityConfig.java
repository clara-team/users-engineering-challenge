package com.clara.taskly.web;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Minimal security config for @WebMvcTest slices.
 *
 * Enables @PreAuthorize, disables CSRF, and provides a no-op JwtDecoder
 * (the jwt() test helper sets Authentication directly — the decoder is never called).
 *
 * Import this in every controller test:
 *   @WebMvcTest(YourController.class)
 *   @Import(TestWebSecurityConfig.class)
 */
@TestConfiguration
@EnableMethodSecurity
public class TestWebSecurityConfig {

    @Bean
    SecurityFilterChain testFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().denyAll())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        // Never called — jwt() helper sets the Authentication directly.
        return token -> Jwt.withTokenValue("test").header("alg", "none").claim("sub", "test").build();
    }
}
