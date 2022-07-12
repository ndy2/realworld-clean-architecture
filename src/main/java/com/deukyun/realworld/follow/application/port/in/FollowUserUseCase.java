package com.deukyun.realworld.follow.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;

public interface FollowUserUseCase {

    FollowUserResult userIdFollowsUsername(UserId userId, String username);
}
