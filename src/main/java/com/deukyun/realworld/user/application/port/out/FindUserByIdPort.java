package com.deukyun.realworld.user.application.port.out;

import com.deukyun.realworld.user.application.port.out.dto.query.FindUserByIdResult;

public interface FindUserByIdPort {

    FindUserByIdResult findUserById(long userId);
}
