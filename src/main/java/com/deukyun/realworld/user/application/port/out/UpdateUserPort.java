package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.application.port.out.dto.command.UpdateUserCommand;
import com.deukyun.realworld.user.application.port.out.dto.command.UpdateUserResult;

public interface UpdateUserPort {

    UpdateUserResult updateUser(UpdateUserCommand updateUserCommand);
}
