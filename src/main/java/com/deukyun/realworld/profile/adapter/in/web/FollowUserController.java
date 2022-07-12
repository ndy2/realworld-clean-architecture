package com.deukyun.realworld.profile.adapter.in.web;

import com.deukyun.realworld.common.SecurityUser;
import com.deukyun.realworld.follow.application.port.in.FollowUserUseCases;
import com.deukyun.realworld.follow.application.port.in.dto.query.FollowUserResult;
import com.deukyun.realworld.profile.adapter.in.dto.command.FollowUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FollowUserController {

    private final FollowUserUseCases followUserUseCases;

    @PostMapping("/api/profiles/{username}/follow")
    public FollowUserResponse followUser(
            @AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable String username
    ) {
        long userId = securityUser.getUserId();

        FollowUserResult followUserResult = followUserUseCases.userIdFollowsUsername(userId, username);

        return new FollowUserResponse(
                followUserResult.getUsername(),
                followUserResult.getBio(),
                followUserResult.getImage(),
                followUserResult.isFollowing()
        );
    }

    @DeleteMapping("/api/profiles/{username}/follow")
    public FollowUserResponse unFollowUser(
            @AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable String username
    ) {
        long userId = securityUser.getUserId();

        FollowUserResult unfollowUserResult = followUserUseCases.userIdUnfollowsUsername(userId, username);

        return new FollowUserResponse(
                unfollowUserResult.getUsername(),
                unfollowUserResult.getBio(),
                unfollowUserResult.getImage(),
                unfollowUserResult.isFollowing()
        );
    }

}
