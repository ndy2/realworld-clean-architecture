package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.profile.application.port.in.RegisterProfileCommand;
import com.deukyun.realworld.profile.application.port.in.RegisterProfileUseCase;
import com.deukyun.realworld.user.application.port.in.CustomPasswordEncoder;
import com.deukyun.realworld.user.application.port.in.RegisterUserCommand;
import com.deukyun.realworld.user.application.port.in.RegisterUserUseCase;
import com.deukyun.realworld.user.application.port.out.InsertUserCommand;
import com.deukyun.realworld.user.application.port.out.InsertUserPort;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
class RegisterUserService implements RegisterUserUseCase {

    private final CustomPasswordEncoder passwordEncoder;
    private final InsertUserPort insertUserPort;
    private final RegisterProfileUseCase insertProfileInPort;

    @Override
    @Transactional
    public void registerUser(RegisterUserCommand registerUserCommand) {

        Email email = registerUserCommand.getEmail();
        Password password = registerUserCommand.getPassword();

        long userId = insertUserPort.insertUser(
                new InsertUserCommand(
                        email,
                        passwordEncoder.encode(password)
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
