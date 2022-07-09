package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.profile.application.port.in.EditProfileCommand;
import com.deukyun.realworld.profile.application.port.in.EditProfileResult;
import com.deukyun.realworld.profile.application.port.in.EditProfileUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class EditProfileService implements EditProfileUseCase {

    @Override
    public EditProfileResult editProfile(EditProfileCommand editProfileCommand) {
        return null;
    }
}
