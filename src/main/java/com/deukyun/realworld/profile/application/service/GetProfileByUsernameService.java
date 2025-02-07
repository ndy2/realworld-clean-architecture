package com.deukyun.realworld.profile.application.service;

import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUsernameQuery;
import com.deukyun.realworld.profile.application.port.in.dto.query.GetProfileByUsernameResult;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUsernamePort;
import com.deukyun.realworld.profile.application.port.out.FindProfileIdByUserIdPort;
import com.deukyun.realworld.profile.application.port.out.dto.query.FindProfileByUsernameResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Query
class GetProfileByUsernameService implements GetProfileByUsernameQuery {

    private final FindProfileIdByUserIdPort findProfileIdByUserIdPort;
    private final FindProfileByUsernamePort findProfileByUsernamePort;
    private final CheckFollowPort checkFollowPort;

    @Override
    public GetProfileByUsernameResult getProfileByUsername(Long userId, String username) {
        long userProfileId = findProfileIdByUserIdPort.findProfileIdByUserId(userId);
        FindProfileByUsernameResult findResult = findProfileByUsernamePort.findProfileByUsername(username);

        boolean isFollow = false;
        if (userId != null) {
            isFollow = checkFollowPort.checkFollow(userProfileId, findResult.getId()).isPresent();
        }

        return new GetProfileByUsernameResult(
                findResult.getUsername(),
                findResult.getBio(),
                findResult.getImage(),
                isFollow
        );
    }
}
