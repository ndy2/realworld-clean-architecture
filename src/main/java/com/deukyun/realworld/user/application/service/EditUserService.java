package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.user.application.port.in.EditUserUseCase;
import com.deukyun.realworld.user.application.port.in.dto.command.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.dto.command.EditUserResult;
import com.deukyun.realworld.user.application.port.out.UpdateUserPort;
import com.deukyun.realworld.user.application.port.out.dto.command.UpdateUserCommand;
import com.deukyun.realworld.user.application.port.out.dto.command.UpdateUserResult;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
class EditUserService implements EditUserUseCase {

    private final UpdateUserPort updateUserPort;

    @Transactional
    @Override
    public EditUserResult editUser(EditUserCommand editUserCommand) {

        UpdateUserResult updateUserResult = updateUserPort.updateUser(
                new UpdateUserCommand(
                        editUserCommand.getId(),
                        editUserCommand.getEmail(),
                        editUserCommand.getPassword()
                )
        );

        return new EditUserResult(
                updateUserResult.getEmail()
        );
    }
}
