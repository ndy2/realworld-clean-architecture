package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.profile.application.port.in.GetProfileQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileResult;
import com.deukyun.realworld.profile.application.port.out.FindProfilePort;
import com.deukyun.realworld.profile.application.port.out.ProfileOutResponse;
import lombok.RequiredArgsConstructor;

/**
 * 아웃 포트로 위임하여 전달 만 함
 */
@RequiredArgsConstructor
@Query
class GetProfileService implements GetProfileQuery {

    private final FindProfilePort findProfilePort;

    @Override
    public GetProfileResult getByUserId(Long id) {

        ProfileOutResponse profileOutResponse = findProfilePort.findByUserId(id);

        return new GetProfileResult(
                profileOutResponse.getUsername(),
                profileOutResponse.getBio(),
                profileOutResponse.getImage()
        );
    }
}
