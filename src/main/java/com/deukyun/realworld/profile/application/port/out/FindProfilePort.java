package com.deukyun.realworld.profile.application.port.out;

public interface FindProfilePort {

    ProfileResponse findByUserId(long userId);
}
