package com.deukyun.realworld.user.application.port.in;

import com.deukyun.realworld.user.application.port.in.dto.command.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.dto.command.EditUserResult;

public interface EditUserUseCase {

    EditUserResult editUser(EditUserCommand editUserCommand);
}
