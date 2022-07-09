package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.user.application.port.in.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.EditUserUseCase;
import com.deukyun.realworld.user.application.port.out.UpdateUserCommand;
import com.deukyun.realworld.user.application.port.out.UpdateUserPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class EditUserService implements EditUserUseCase {

    private final UpdateUserPort updateUserPort;

    @Override
    public void editUser(EditUserCommand editUserRequest) {

        updateUserPort.updateUser(
                new UpdateUserCommand(
                        editUserRequest.getEmail(),
                        editUserRequest.getPassword()
                )
        );
    }
}
