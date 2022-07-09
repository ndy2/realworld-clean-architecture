package com.deukyun.realworld.profile.application.port.in;

public interface GetProfileQuery {

    GetProfileResult getByUserId(Long id);
}
