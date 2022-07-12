package com.deukyun.realworld.profile.application.port.out;

import com.deukyun.realworld.profile.application.port.out.dto.command.UpdateProfileCommand;
import com.deukyun.realworld.profile.application.port.out.dto.command.UpdateProfileResult;

public interface UpdateProfilePort {

    UpdateProfileResult updatePort(UpdateProfileCommand updateProfileCommand);
}
