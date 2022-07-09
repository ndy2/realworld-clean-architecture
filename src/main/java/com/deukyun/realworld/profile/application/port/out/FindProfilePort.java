package com.deukyun.realworld.profile.application.port.out;

public interface FindProfilePort {

    FindProfileResult findByUserId(long userId);
}
