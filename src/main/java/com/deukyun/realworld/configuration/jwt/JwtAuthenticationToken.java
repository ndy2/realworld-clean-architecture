package com.deukyun.realworld.configuration.jwt;

import lombok.Getter;
import lombok.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;

    /**
     * 인증되지 않은 토큰
     *
     * @param principal   - 이메일
     * @param credentials - 비밀번호
     */
    public JwtAuthenticationToken(Object principal, Object credentials) {
        super(Collections.emptySet());
        super.setAuthenticated(false);

        this.principal = principal;
        this.credentials = credentials;
    }

    /**
     * @param principal - id
     */
    public JwtAuthenticationToken(JwtAuthentication principal) {
        super(Collections.emptySet());
        super.setAuthenticated(true);

        this.principal = principal;
        this.credentials = null;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

    @Value
    public static class JwtAuthentication {

        long userId;
        String token;
    }
}
