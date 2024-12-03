package com.spring.app.postify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Desabilita CSRF, útil para APIs sem autenticação
                .authorizeRequests()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()  // Libera Swagger e documentação da API
                .requestMatchers("/api/**").permitAll()  // Libera as APIs
                .anyRequest().permitAll();  // Libera qualquer outra rota

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}