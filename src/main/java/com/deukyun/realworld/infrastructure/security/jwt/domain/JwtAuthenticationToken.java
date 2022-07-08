package com.deukyun.realworld.infrastructure.security.jwt.domain;

import lombok.Getter;
import lombok.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

import static com.google.common.base.Preconditions.checkState;

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
     * 인증된 토큰
     * <p>
     * TODO @param principal - 인증 객체, 지금은 그냥 email, boolean 만 체크함
     */
    public JwtAuthenticationToken(Object principal) {
        super(Collections.emptySet());
        super.setAuthenticated(true);

        this.principal = principal;
        this.credentials = null;
    }

    public Long getId() {
        checkState(isAuthenticated(), "authentication is not provided");

        return ((JwtAuthentication) principal).getId();
    }

    public String getJwtString() {
        checkState(isAuthenticated(), "authentication is not provided");

        return ((JwtAuthentication) principal).getToken();
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

    @Value
    class JwtAuthentication {

        Long id;
        String token;
    }
}
