package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.profile.application.port.in.EditProfileCommand;
import com.deukyun.realworld.profile.application.port.out.UpdateProfileCommand;
import com.deukyun.realworld.profile.application.port.out.UpdateProfilePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class EditProfileServiceTest {

    EditProfileService editProfileService;

    UpdateProfilePort updateProfilePort = mock(UpdateProfilePort.class);

    @BeforeEach
    void setUp() {
        editProfileService = new EditProfileService(updateProfilePort);
    }

    @Test
    void 프로필_업데이트() {
        //given
        EditProfileCommand editProfileCommand =
                new EditProfileCommand(1L, "editname", "editbio", "editiamge");

        //when
        editProfileService.editProfile(editProfileCommand);

        //then
        verify(updateProfilePort)
                .updatePort(new UpdateProfileCommand(1L, "editname", "editbio", "editiamge"));
    }
}