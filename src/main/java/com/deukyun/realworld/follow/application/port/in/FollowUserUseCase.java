package com.deukyun.realworld.follow.application.port.in;

public interface FollowUserUseCase {

    FollowUserResult userIdFollowsUsername(long userId, String username);
}
