package com.deukyun.realworld.follow.application.port.out;

import com.deukyun.realworld.profile.domain.Profile.ProfileId;

public interface DeleteFollowPort {

    void deleteById(ProfileId id);
}
