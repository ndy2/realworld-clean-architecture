package com.deukyun.realworld.user.application.port.in;

import com.deukyun.realworld.user.application.port.in.dto.query.GetUserByIdResult;

public interface GetUserByIdQuery {
    GetUserByIdResult getUserById(long id);
}
