package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.domain.User.UserId;

public interface FindUserByIdPort {

    FindUserByIdResult findUserById(UserId userId);
}
