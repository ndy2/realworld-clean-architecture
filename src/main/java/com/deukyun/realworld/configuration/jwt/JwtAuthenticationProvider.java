package com.deukyun.realworld.configuration.jwt;

import com.deukyun.realworld.configuration.jwt.JwtAuthenticationToken.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtResolver jwtResolver;
    private final JwtAuthenticationUseCase authenticationUseCase;

    /**
     * @param authentication 미 인증 인증객체
     * @return 인증된 인증 객체
     * @throws AuthenticationException - 인증 예외
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;

        long userId = authenticationUseCase.authenticate(
                jwtAuthenticationToken.getPrincipal(),
                jwtAuthenticationToken.getCredentials()
        );

        String token = jwtResolver.generate(String.valueOf(userId));

        return new JwtAuthenticationToken(
                new JwtAuthentication(userId, token)
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
