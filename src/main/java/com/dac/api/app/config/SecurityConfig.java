package com.dac.api.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.dac.api.app.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private static final String[] AUTH_WHITELIST = {
                        // -- Swagger UI v2
                        "/v2/api-docs",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        // -- Swagger UI v3 (OpenAPI)
                        "/v3/api-docs/**",
                        "/swagger-ui/**",

                        // public endpoints
                        "/api/auth/**",
                        "/api/users/**"
        };

        private static final String[] ADMIN_POST_WHITELIST = {
                        // admin endpoints
                        "/api/events/",
                        "/api/editions/"
        };

        private static final String[] ADMIN_DELETE_WHITELIST = {
                        // admin endpoints
                        "/api/events/**",
                        "/api/editions/**",
        };

        private static final String[] ADMIN_PATCH_WHITELIST = {
                        // admin endpoints
                        "/api/editions/**"
        };

        private static final String[] ADMIN_PUT_WHITELIST = {
                        // admin endpoints
                        // "/api/editions/**"
        };

        @Autowired
        private SecurityFilter securityFilter;

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                                .requestMatchers(HttpMethod.POST, ADMIN_POST_WHITELIST).hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.DELETE, ADMIN_DELETE_WHITELIST)
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PATCH, ADMIN_PATCH_WHITELIST)
                                                .hasRole("ADMIN")
                                                .requestMatchers(HttpMethod.PUT, ADMIN_PUT_WHITELIST)
                                                .hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
