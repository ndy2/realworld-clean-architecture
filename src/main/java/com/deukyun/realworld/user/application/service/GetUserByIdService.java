package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.user.application.port.in.GetUserByIdQuery;
import com.deukyun.realworld.user.application.port.in.GetUserByIdResult;
import com.deukyun.realworld.user.application.port.out.FindUserByIdPort;
import com.deukyun.realworld.user.application.port.out.FindUserByIdResult;
import com.deukyun.realworld.user.domain.User.UserId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Query
public class GetUserByIdService implements GetUserByIdQuery {

    private final FindUserByIdPort findUserByIdPort;

    @Override
    public GetUserByIdResult getUserById(UserId id) {
        FindUserByIdResult findUserByIdResult = findUserByIdPort.findUserById(id);

        return new GetUserByIdResult(findUserByIdResult.getEmail());
    }
}
