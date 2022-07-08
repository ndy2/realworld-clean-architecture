package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.profile.application.port.in.GetProfileQuery;
import com.deukyun.realworld.profile.application.port.in.ProfileInResponse;
import com.deukyun.realworld.profile.application.port.out.FindProfilePort;
import com.deukyun.realworld.profile.application.port.out.ProfileOutResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Query
public class GetProfileService implements GetProfileQuery {

    private final FindProfilePort findProfilePort;

    @Override
    public ProfileInResponse getByUserId(Long id) {

        ProfileOutResponse profileOutResponse = findProfilePort.findByUserId(id);

        return new ProfileInResponse(
                profileOutResponse.getUsername(),
                profileOutResponse.getBio(),
                profileOutResponse.getImage()
        );
    }
}
