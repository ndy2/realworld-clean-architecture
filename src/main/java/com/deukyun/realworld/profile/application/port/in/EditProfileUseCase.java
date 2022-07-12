package com.deukyun.realworld.profile.application.port.in;

import com.deukyun.realworld.profile.application.port.in.dto.command.EditProfileCommand;
import com.deukyun.realworld.profile.application.port.in.dto.command.EditProfileResult;

public interface EditProfileUseCase {

    EditProfileResult editProfile(EditProfileCommand editProfileCommand);
}
