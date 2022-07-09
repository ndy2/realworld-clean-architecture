package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.user.application.port.in.EditUserCommand;
import com.deukyun.realworld.user.application.port.out.UpdateUserCommand;
import com.deukyun.realworld.user.application.port.out.UpdateUserPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

        //when
        editUserService.editUser(editUserCommand);

        //then
        verify(updateUserPort)
                .updateUser(new UpdateUserCommand(1L, "edit@edit.edit", "editedit"));
    }
}