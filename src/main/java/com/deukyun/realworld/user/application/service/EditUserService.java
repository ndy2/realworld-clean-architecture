package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.user.application.port.in.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.EditUserUseCase;
import com.deukyun.realworld.user.application.port.out.UpdateUserCommand;
import com.deukyun.realworld.user.application.port.out.UpdateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
class EditUserService implements EditUserUseCase {

    private final UpdateUserPort updateUserPort;

    @Transactional
    @Override
    public void editUser(EditUserCommand editUserCommand) {

        updateUserPort.updateUser(
                new UpdateUserCommand(
                        editUserCommand.getId(),
                        editUserCommand.getEmail(),
                        editUserCommand.getPassword()
                )
        );
    }
}
