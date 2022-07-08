package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.profile.application.port.in.GetProfileQuery;
import com.deukyun.realworld.profile.application.port.in.ProfileResponse;

@Query
public class GetProfileService implements GetProfileQuery {

    @Override
    public ProfileResponse getByUserId(Long id) {
        return null;
    }
}
