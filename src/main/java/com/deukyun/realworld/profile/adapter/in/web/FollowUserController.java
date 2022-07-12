package com.deukyun.realworld.profile.adapter.in.web;

import com.deukyun.realworld.common.SecurityUser;
import com.deukyun.realworld.follow.application.port.in.FollowUserResult;
import com.deukyun.realworld.follow.application.port.in.FollowUserUseCase;
import com.deukyun.realworld.follow.application.port.in.UnfollowUserUseCase;
import com.deukyun.realworld.user.domain.User.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FollowUserController {

    private final FollowUserUseCase followUserUseCase;
    private final UnfollowUserUseCase unfollowUserUseCase;

    @PostMapping("/api/profiles/{username}/follow")
    public FollowUserResponse followUser(
            @AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable String username
    ) {
        UserId userId = new UserId(securityUser.getUserId());

        FollowUserResult followUserResult = followUserUseCase.userIdFollowsUsername(userId, username);

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
        UserId userId = new UserId(securityUser.getUserId());

        FollowUserResult unfollowUserResult = unfollowUserUseCase.userIdUnfollowsUsername(userId, username);

        return new FollowUserResponse(
                unfollowUserResult.getUsername(),
                unfollowUserResult.getBio(),
                unfollowUserResult.getImage(),
                unfollowUserResult.isFollowing()
        );
    }

}
