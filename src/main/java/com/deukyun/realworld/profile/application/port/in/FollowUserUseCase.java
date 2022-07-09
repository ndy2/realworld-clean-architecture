package com.deukyun.realworld.profile.application.port.in;

public interface FollowUserUseCase {

    FollowUserResult userIdFollowsUsername(long userId, String username);
}
