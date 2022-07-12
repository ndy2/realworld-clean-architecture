package com.deukyun.realworld.user.application.service;

import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.user.application.port.in.GetUserByIdQuery;
import com.deukyun.realworld.user.application.port.in.dto.query.GetUserByIdResult;
import com.deukyun.realworld.user.application.port.out.FindUserByIdPort;
import com.deukyun.realworld.user.application.port.out.dto.query.FindUserByIdResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Query
public class GetUserByIdService implements GetUserByIdQuery {

    private final FindUserByIdPort findUserByIdPort;

    @Override
    public GetUserByIdResult getUserById(long id) {
        FindUserByIdResult findUserByIdResult = findUserByIdPort.findUserById(id);

        return new GetUserByIdResult(
                findUserByIdResult.getEmail()
        );
    }
}
