package com.cdac.campussync.Config;

import com.cdac.campussync.Security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> {authorize.requestMatchers("/admin/**", "/api/**").hasRole("ADMIN");});
        http.authorizeHttpRequests(authorize -> {authorize.requestMatchers("/teacher/**", "/api/**").hasRole("TEACHER");});
        http.authorizeHttpRequests(authorize -> {authorize.requestMatchers("/student/**", "/api/**").hasRole("STUDENT");});
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/login", "/auth/logout").permitAll());
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);

        http.logout(logout -> logout.logoutSuccessUrl("/auth/logout").invalidateHttpSession(true).clearAuthentication(true));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

