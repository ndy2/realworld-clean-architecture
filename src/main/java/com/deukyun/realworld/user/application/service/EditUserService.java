package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.user.application.port.in.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.EditUserResult;
import com.deukyun.realworld.user.application.port.in.EditUserUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class EditUserService implements EditUserUseCase {
    @Override
    public EditUserResult editUser(EditUserCommand editUserRequest) {
        return null;
    }
}
