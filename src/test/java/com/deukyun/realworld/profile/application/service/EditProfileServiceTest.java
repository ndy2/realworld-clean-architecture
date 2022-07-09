package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.profile.application.port.in.EditProfileCommand;
import com.deukyun.realworld.profile.application.port.in.EditProfileResult;
import com.deukyun.realworld.profile.application.port.out.UpdateProfileCommand;
import com.deukyun.realworld.profile.application.port.out.UpdateProfilePort;
import com.deukyun.realworld.profile.application.port.out.UpdateProfileResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
                new EditProfileCommand(1L, "editName", "editBio", "editImage");

        when(updateProfilePort.updatePort(
                new UpdateProfileCommand(1L, "editName", "editBio", "editImage")
        )).thenReturn(
                new UpdateProfileResult("editName", "editBio", "editImage"));


        //when
        EditProfileResult editProfileResult = editProfileService.editProfile(editProfileCommand);

        //then
        verify(updateProfilePort)
                .updatePort(new UpdateProfileCommand(1L, "editName", "editBio", "editImage"));
        assertThat(editProfileResult)
                .isEqualTo(new EditProfileResult("editName", "editBio", "editImage"));
    }
}