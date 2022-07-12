package com.deukyun.realworld.follow.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;

public interface UnfollowUserUseCase {

    FollowUserResult userIdUnfollowsUsername(UserId userId, String username);
}
