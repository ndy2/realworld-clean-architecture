package com.deukyun.realworld.profile.application.port.out;

import com.deukyun.realworld.profile.application.port.out.dto.query.FindProfileByUserIdResult;

public interface FindProfileByUserIdPort {

    FindProfileByUserIdResult findByUserId(long userId);
}
