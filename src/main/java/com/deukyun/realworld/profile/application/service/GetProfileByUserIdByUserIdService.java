package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdResult;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Query
class GetProfileByUserIdByUserIdService implements GetProfileByUserIdQuery {

    private final FindProfileByUserIdPort findProfileByUserIdPort;

    @Override
    public GetProfileByUserIdResult getProfileByUserId(Long id) {

        FindProfileByUserIdResult findProfileByUserIdResult = findProfileByUserIdPort.findByUserId(id);

        return new GetProfileByUserIdResult(
                findProfileByUserIdResult.getId(),
                findProfileByUserIdResult.getEmail(),
                findProfileByUserIdResult.getUsername(),
                findProfileByUserIdResult.getBio(),
                findProfileByUserIdResult.getImage()
        );
    }
}
