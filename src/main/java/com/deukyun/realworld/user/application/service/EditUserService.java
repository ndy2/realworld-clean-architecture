package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.user.application.port.in.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.EditUserUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class EditUserService implements EditUserUseCase {


    @Override
    public void editUser(EditUserCommand editUserRequest) {

    }
}
