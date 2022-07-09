package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUsernameQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUsernameResult;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUsernamePort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUsernameResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Query
class GetProfileByUsernameService implements GetProfileByUsernameQuery {

    private final FindProfileByUsernamePort findProfileByUsernamePort;

    @Override
    public GetProfileByUsernameResult getProfileByUsername(String username) {

        FindProfileByUsernameResult findResult = findProfileByUsernamePort.findProfileByUsername(username);

        return new GetProfileByUsernameResult(
                findResult.getUsername(),
                findResult.getBio(),
                findResult.getImage()
        );
    }
}
