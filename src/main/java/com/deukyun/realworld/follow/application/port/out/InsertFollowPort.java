package com.deukyun.realworld.follow.application.port.out;

import com.deukyun.realworld.profile.domain.Profile.ProfileId;

public interface InsertFollowPort {

    void insertFollow(ProfileId followerId, ProfileId followeeId);
}
