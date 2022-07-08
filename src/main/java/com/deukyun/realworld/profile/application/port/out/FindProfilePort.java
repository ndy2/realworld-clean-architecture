package com.deukyun.realworld.profile.application.port.out;

public interface FindProfilePort {

    ProfileOutResponse findByUserId(long userId);
}
