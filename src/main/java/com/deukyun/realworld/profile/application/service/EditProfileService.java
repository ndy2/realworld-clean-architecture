package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.profile.application.port.in.EditProfileCommand;
import com.deukyun.realworld.profile.application.port.in.EditProfileUseCase;
import com.deukyun.realworld.profile.application.port.out.UpdateProfileCommand;
import com.deukyun.realworld.profile.application.port.out.UpdateProfilePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class EditProfileService implements EditProfileUseCase {

    private final UpdateProfilePort updateProfilePort;

    @Override
    public void editProfile(EditProfileCommand editProfileCommand) {

        updateProfilePort.updatePort(
                new UpdateProfileCommand(
                        editProfileCommand.getUserId(),
                        editProfileCommand.getUsername(),
                        editProfileCommand.getBio(),
                        editProfileCommand.getImage()
                )
        );
    }
}
