package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.profile.application.port.in.RegisterProfileCommand;
import com.deukyun.realworld.profile.application.port.in.RegisterProfileUseCase;
import com.deukyun.realworld.user.application.port.in.RegisterUserCommand;
import com.deukyun.realworld.user.application.port.in.RegisterUserUseCase;
import com.deukyun.realworld.user.application.port.out.InsertUserCommand;
import com.deukyun.realworld.user.application.port.out.InsertUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
class RegisterUserService implements RegisterUserUseCase {

    private final PasswordEncoder passwordEncoder;
    private final InsertUserPort insertUserPort;
    private final RegisterProfileUseCase insertProfileInPort;

    @Override
    @Transactional
    public void registerUser(RegisterUserCommand registerUserCommand) {

        String password = registerUserCommand.getPassword();
        String encodedPassword = passwordEncoder.encode(password);

        long userId = insertUserPort.insertUser(
                new InsertUserCommand(
                        registerUserCommand.getEmail(),
                        encodedPassword
                )
        );

        insertProfileInPort.registerProfile(
                new RegisterProfileCommand(
                        userId,
                        registerUserCommand.getUsername()
                )
        );
    }
}
