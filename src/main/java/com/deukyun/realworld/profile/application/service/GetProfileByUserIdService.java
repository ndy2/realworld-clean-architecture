package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.profile.application.port.in.GetProfileQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileResult;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdResult;
import lombok.RequiredArgsConstructor;

/**
 * 아웃 포트로 위임하여 전달 만 함
 */
@RequiredArgsConstructor
@Query
class GetProfileByUserIdService implements GetProfileQuery {

    private final FindProfileByUserIdPort findProfileByUserIdPort;

    @Override
    public GetProfileResult getByUserId(Long id) {

        FindProfileByUserIdResult findProfileByUserIdResult = findProfileByUserIdPort.findByUserId(id);

        return new GetProfileResult(
                findProfileByUserIdResult.getUsername(),
                findProfileByUserIdResult.getBio(),
                findProfileByUserIdResult.getImage()
        );
    }
}
