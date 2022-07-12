package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.user.application.port.in.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.EditUserResult;
import com.deukyun.realworld.user.application.port.in.EditUserUseCase;
import com.deukyun.realworld.user.application.port.out.UpdateUserCommand;
import com.deukyun.realworld.user.application.port.out.UpdateUserPort;
import com.deukyun.realworld.user.application.port.out.UpdateUserResult;
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
                        editUserCommand.getUserId(),
                        editUserCommand.getEmail(),
                        editUserCommand.getPassword()
                )
        );

        return new EditUserResult(
                updateUserResult.getEmail()
        );
    }
}
