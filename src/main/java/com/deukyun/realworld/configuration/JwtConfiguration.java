package com.deukyun.realworld.configuration;

import com.deukyun.realworld.infrastructure.security.jwt.JwtAuthenticationFilter;
import com.deukyun.realworld.infrastructure.security.jwt.JwtAuthenticationProvider;
import com.deukyun.realworld.infrastructure.security.jwt.JwtResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final JwtResolver jwtResolver;

    @Override
    public void configure(HttpSecurity http) {
        http
                .authenticationProvider(jwtAuthenticationProvider)
                .addFilterAfter(new JwtAuthenticationFilter(jwtResolver), SecurityContextPersistenceFilter.class);
    }
}