package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.user.application.port.in.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.EditUserResult;
import com.deukyun.realworld.user.application.port.out.UpdateUserCommand;
import com.deukyun.realworld.user.application.port.out.UpdateUserPort;
import com.deukyun.realworld.user.application.port.out.UpdateUserResult;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import com.deukyun.realworld.user.domain.User.UserId;
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
        EditUserCommand editUserCommand = new EditUserCommand(
                new UserId(1L),
                new Email("edit@edit.edit"),
                new Password("editedit")
        );
        when(updateUserPort.updateUser(new UpdateUserCommand(
                        new UserId(1L),
                        new Email("edit@edit.edit"),
                        new Password("editedit")
                )
        )).thenReturn(
                new UpdateUserResult(new Email("edit@edit.edit"))
        );


        //when
        EditUserResult editUserResult = editUserService.editUser(editUserCommand);

        //then
        verify(updateUserPort).updateUser(new UpdateUserCommand(
                new UserId(1L),
                new Email("edit@edit.edit"),
                new Password("editedit")
        ));
        assertThat(editUserResult)
                .isEqualTo(new EditUserResult(new Email("edit@edit.edit")));
    }
}