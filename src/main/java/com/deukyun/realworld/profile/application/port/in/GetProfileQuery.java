package com.deukyun.realworld.profile.application.port.in;

public interface GetProfileQuery {

    ProfileInResponse getByUserId(Long id);
}
