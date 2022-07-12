package com.deukyun.realworld.user.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;

public interface GetUserByIdQuery {
    GetUserByIdResult getUserById(UserId userId);
}
