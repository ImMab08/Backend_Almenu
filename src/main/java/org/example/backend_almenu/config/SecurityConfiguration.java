package org.example.backend_almenu.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf ->
                        csrf.disable()
                        )
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
                        )
                .formLogin(Customizer.withDefaults())
                .build();
    }

}
