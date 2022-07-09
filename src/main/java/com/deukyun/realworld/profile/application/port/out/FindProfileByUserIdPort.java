package com.deukyun.realworld.profile.application.port.out;

public interface FindProfileByUserIdPort {

    FindProfileByUserIdResult findByUserId(long userId);
}
