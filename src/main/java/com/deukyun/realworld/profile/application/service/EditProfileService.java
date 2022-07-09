package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.profile.application.port.in.EditProfileCommand;
import com.deukyun.realworld.profile.application.port.in.EditProfileResult;
import com.deukyun.realworld.profile.application.port.in.EditProfileUseCase;
import com.deukyun.realworld.profile.application.port.out.UpdateProfileCommand;
import com.deukyun.realworld.profile.application.port.out.UpdateProfilePort;
import com.deukyun.realworld.profile.application.port.out.UpdateProfileResult;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
class EditProfileService implements EditProfileUseCase {

    private final UpdateProfilePort updateProfilePort;

    @Transactional
    @Override
    public EditProfileResult editProfile(EditProfileCommand editProfileCommand) {

        UpdateProfileResult updateProfileResult = updateProfilePort.updatePort(
                new UpdateProfileCommand(
                        editProfileCommand.getUserId(),
                        editProfileCommand.getUsername(),
                        editProfileCommand.getBio(),
                        editProfileCommand.getImage()
                )
        );

        return new EditProfileResult(
                updateProfileResult.getUsername(),
                updateProfileResult.getBio(),
                updateProfileResult.getImage()
        );
    }
}
