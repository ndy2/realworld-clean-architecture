package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.user.application.port.in.dto.command.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.dto.command.EditUserResult;
import com.deukyun.realworld.user.application.port.out.UpdateUserPort;
import com.deukyun.realworld.user.application.port.out.dto.command.UpdateUserCommand;
import com.deukyun.realworld.user.application.port.out.dto.command.UpdateUserResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class EditUserServiceTest {

    EditUserService editUserService;

    UpdateUserPort updateUserPort = mock(UpdateUserPort.class);

    @BeforeEach
    void setUp() {
        editUserService = new EditUserService(updateUserPort);
    }

    @Test
    void 사용자_업데이트() {
        //given
        EditUserCommand editUserCommand = new EditUserCommand(1L, "edit@edit.edit", "editedit");
        when(updateUserPort.updateUser(
                new UpdateUserCommand(1L, "edit@edit.edit", "editedit")
        )).thenReturn(
                new UpdateUserResult("edit@edit.edit")
        );


        //when
        EditUserResult editUserResult = editUserService.editUser(editUserCommand);

        //then
        verify(updateUserPort)
                .updateUser(new UpdateUserCommand(1L, "edit@edit.edit", "editedit"));
        assertThat(editUserResult)
                .isEqualTo(new EditUserResult("edit@edit.edit"));
    }
}