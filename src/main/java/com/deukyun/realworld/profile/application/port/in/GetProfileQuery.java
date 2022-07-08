package com.deukyun.realworld.profile.application.port.in;

public interface GetProfileQuery {

    ProfileResponse getByUserId(Long id);
}
