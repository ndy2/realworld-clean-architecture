package com.deukyun.realworld.follow.application.port.in;

public interface UnfollowUserUseCase {

    FollowUserResult userIdUnfollowsUsername(long userId, String username);
}
