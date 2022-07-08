package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.profile.application.port.in.InsertProfileInPort;
import com.deukyun.realworld.user.application.port.in.RegisterUserCommand;
import com.deukyun.realworld.user.application.port.in.RegisterUserUseCase;
import com.deukyun.realworld.user.application.port.out.InsertUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class RegisterUserService implements RegisterUserUseCase {

    private final InsertUserPort insertUserPort;
    private final InsertProfileInPort insertProfileInPort;

    @Override
    public void registerUser(RegisterUserCommand registerUserCommand) {

        insertProfileInPort.insertProfile(
                registerUserCommand.getUsername()
        );

        insertUserPort.insertUser(
                registerUserCommand.getEmail(),
                registerUserCommand.getPassword()
        );
    }
}
