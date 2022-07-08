package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.infrastructure.security.jwt.application.port.in.JwtAuthenticationUseCase;
import com.deukyun.realworld.user.application.port.out.FindPasswordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@UseCase
public class AuthenticationService implements JwtAuthenticationUseCase {

    private final FindPasswordPort findUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void authenticate(Object principal, Object credentials) {

        String email = String.valueOf(principal);
        String password = String.valueOf(credentials);

        String findPassword = findUserPort.findPasswordByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("invalid email or password provided")
        );

        boolean isAuthenticated = passwordEncoder.matches(password, findPassword);
        if (!isAuthenticated) {
            throw new IllegalArgumentException("invalid email or password provided");
        }
    }
}
