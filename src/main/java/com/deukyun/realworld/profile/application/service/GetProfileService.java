package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.profile.application.port.in.GetProfileQuery;
import com.deukyun.realworld.profile.application.port.in.ProfileResponse;
import com.deukyun.realworld.profile.application.port.out.FindProfilePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Query
public class GetProfileService implements GetProfileQuery {

    private final FindProfilePort findProfilePort;

    @Override
    public ProfileResponse getByUserId(Long id) {

        return null;
    }
}
