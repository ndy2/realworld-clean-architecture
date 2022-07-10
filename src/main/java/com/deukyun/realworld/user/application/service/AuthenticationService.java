package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.configuration.jwt.JwtAuthenticationUseCase;
import com.deukyun.realworld.user.application.port.out.FindPasswordPort;
import com.deukyun.realworld.user.application.port.out.FindPasswordResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.google.common.base.Preconditions.checkArgument;

@RequiredArgsConstructor
@UseCase
class AuthenticationService implements JwtAuthenticationUseCase {

    private final FindPasswordPort findUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public long authenticate(Object principal, Object credentials) {

        String email = String.valueOf(principal);
        String password = String.valueOf(credentials);

        FindPasswordResult findPasswordResult = findUserPort
                .findPasswordByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("invalid email or password provided"));

        checkArgument(passwordEncoder.matches(password, findPasswordResult.getPassword()), "invalid email or password provided");

        return findPasswordResult.getId();
    }
}
