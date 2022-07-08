package com.deukyun.realworld.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                // REST API 애플리케이션이기 때문에 불필요한 기능 Disable
                .formLogin().disable()
                .csrf().disable()
                .headers().disable()
                .httpBasic().disable()
                .rememberMe().disable()
                .logout().disable()
                .requestCache().disable()

                .authorizeRequests(
                        auth -> {
                            auth.anyRequest().permitAll();
                        }
                )

                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and().build();
    }
}
