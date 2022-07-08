package com.deukyun.realworld.infrastructure.security.jwt.application.service;

import com.deukyun.realworld.infrastructure.security.jwt.application.port.in.JwtAuthenticationUseCase;
import com.deukyun.realworld.infrastructure.security.jwt.domain.JwtAuthenticationToken;
import com.deukyun.realworld.infrastructure.security.jwt.domain.JwtAuthenticationToken.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtAuthenticationUseCase authenticationUseCase;

    /**
     * @param authentication 미 인증 인증객체
     * @return 인증된 인증 객체
     * @throws AuthenticationException - 인증 예외
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthentication = (JwtAuthenticationToken) authentication;

        long userId = authenticationUseCase.authenticate(jwtAuthentication.getPrincipal(), jwtAuthentication.getCredentials());

        //TODO implement jwt
        String token = "todo.implement.realjwt";

        return new JwtAuthenticationToken(
                new JwtAuthentication(userId, token)
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
