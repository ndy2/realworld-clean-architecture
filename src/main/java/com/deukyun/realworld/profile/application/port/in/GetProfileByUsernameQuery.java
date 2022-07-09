package com.deukyun.realworld.profile.application.port.in;

public interface GetProfileByUsernameQuery {

    GetProfileByUsernameResult getProfileByUsername(String username);
}
