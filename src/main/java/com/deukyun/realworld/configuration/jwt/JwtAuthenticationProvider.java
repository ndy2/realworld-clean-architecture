package com.deukyun.realworld.configuration.jwt;

import com.deukyun.realworld.common.SecurityUser;
import com.deukyun.realworld.user.application.port.in.AuthenticationQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtResolver jwtResolver;
    private final AuthenticationQuery authenticationUseCase;

    /**
     * @param authentication 미 인증 인증객체
     * @return 인증된 인증 객체
     * @throws AuthenticationException - 인증 예외
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        long userId = authenticationUseCase.authenticate(
                authenticationToken.getPrincipal(),
                authenticationToken.getCredentials()
        );

        String token = jwtResolver.generate(String.valueOf(userId));

        return new UsernamePasswordAuthenticationToken(
                new SecurityUser(userId, token), null, Collections.emptySet()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
