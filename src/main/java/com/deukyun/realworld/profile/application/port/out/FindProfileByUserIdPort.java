package com.deukyun.realworld.profile.application.port.out;

import com.deukyun.realworld.user.domain.User.UserId;

public interface FindProfileByUserIdPort {

    FindProfileByUserIdResult findByUserId(UserId userId);
}
