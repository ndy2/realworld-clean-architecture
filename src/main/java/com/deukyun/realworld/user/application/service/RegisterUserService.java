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
import com.deukyun.realworld.user.domain.User.UserId;
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

        // 사용자 삽입
        UserId userId = insertUserPort.insertUser(
                new InsertUserCommand(
                        email,
                        passwordEncoder.encode(password)
                )
        );

        // 프로필 삽입
        insertProfileInPort.registerProfile(
                new RegisterProfileCommand(
                        userId,
                        registerUserCommand.getUsername()
                )
        );
    }

}
