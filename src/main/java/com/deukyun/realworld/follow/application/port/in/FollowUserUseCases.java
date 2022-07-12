package com.deukyun.realworld.follow.application.port.in;

import com.deukyun.realworld.follow.application.port.in.dto.query.FollowUserResult;

public interface FollowUserUseCases {

    FollowUserResult userIdFollowsUsername(long userId, String username);

    FollowUserResult userIdUnfollowsUsername(long userId, String username);
}
