package com.deukyun.realworld.profile.application.port.out;

import com.deukyun.realworld.user.domain.User.UserId;

public interface FindProfileIdByUserIdPort {

    long findProfileIdByUserId(UserId userId);
}
