package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.infrastructure.security.jwt.application.port.in.JwtAuthenticationUseCase;
import com.deukyun.realworld.user.application.port.out.FindPasswordPort;
import com.deukyun.realworld.user.application.port.out.FindPasswordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.google.common.base.Preconditions.checkArgument;

@RequiredArgsConstructor
@UseCase
public class AuthenticationService implements JwtAuthenticationUseCase {

    private final FindPasswordPort findUserPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public long authenticate(Object principal, Object credentials) {

        String email = String.valueOf(principal);
        String password = String.valueOf(credentials);

        FindPasswordResponse findPasswordResponse = findUserPort
                .findPasswordByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("invalid email or password provided"));

        checkArgument(passwordEncoder.matches(password, findPasswordResponse.getPassword()), "invalid email or password provided");

        return findPasswordResponse.getId();
    }
}
