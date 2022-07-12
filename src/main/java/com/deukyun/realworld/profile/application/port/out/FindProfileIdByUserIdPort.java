package com.deukyun.realworld.profile.application.port.out;

import com.deukyun.realworld.profile.domain.Profile.ProfileId;
import com.deukyun.realworld.user.domain.User.UserId;

public interface FindProfileIdByUserIdPort {

    ProfileId findProfileIdByUserId(UserId userId);
}
