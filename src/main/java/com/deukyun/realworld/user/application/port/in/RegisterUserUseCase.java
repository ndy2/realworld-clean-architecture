package com.deukyun.realworld.user.application.port.in;

import com.deukyun.realworld.user.application.port.in.dto.command.RegisterUserCommand;

public interface RegisterUserUseCase {

    void registerUser(RegisterUserCommand registerUserCommand);
}
