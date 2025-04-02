package com.example.back.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.back.security.CustomJwt;
import com.example.back.security.CustomJwtConverter;

import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            .oauth2ResourceServer((oauth) -> oauth.jwt(
                jwt -> jwt.jwtAuthenticationConverter(customJwtConverter())
            ));
        return http.build();
    }

    @Bean
    public Converter<Jwt,CustomJwt> customJwtConverter(){
        return new CustomJwtConverter();
    }
}
