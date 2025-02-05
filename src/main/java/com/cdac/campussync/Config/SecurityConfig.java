package com.cdac.campussync.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorize()
                .requestMatchers("/", "/login", "/register").permitAll()  // Allow access to these URLs without authentication
                .requestMatchers("/admin/**").hasRole("ADMIN") // Only allow access to /admin/** URLs for ADMIN users
                .requestMatchers("/teacher/**").hasRole("TEACHER") // Only allow access to /teacher/** URLs for TEACHER users
                .requestMatchers("/student/**").hasRole("STUDENT") // Only allow access to /student/** URLs for STUDENT users
                .anyRequest().authenticated() // All other requests require authentication
                .and()
                .formLogin() // Enable form-based login
                .loginPage("/login") // Custom login page URL
                .permitAll()
                .and()
                .logout() // Enable logout
                .permitAll();

        return http.build();
    }
}
