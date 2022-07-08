package com.deukyun.realworld.infrastructure.security.jwt.application.service;

import com.deukyun.realworld.infrastructure.security.jwt.application.port.in.JwtAuthenticationUseCase;
import com.deukyun.realworld.infrastructure.security.jwt.domain.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtAuthenticationUseCase authenticationUseCase;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthentication = (JwtAuthenticationToken) authentication;

        return processUserAuthentication(
                jwtAuthentication.getPrincipal(),
                jwtAuthentication.getCredentials()
        );
    }

    private Authentication processUserAuthentication(Object principal, Object credentials) {
        authenticationUseCase.authenticate(principal, credentials);

        return new JwtAuthenticationToken(principal);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
