package com.deukyun.realworld.profile.adapter.in.web;

import com.deukyun.realworld.infrastructure.security.jwt.JwtAuthenticationToken.JwtAuthentication;
import com.deukyun.realworld.profile.application.port.in.FollowUserResult;
import com.deukyun.realworld.profile.application.port.in.FollowUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FollowUserController {

    private final FollowUserUseCase followUserUseCase;

    @PostMapping("/api/profiles/{username}/follow")
    public FollowUserResponse followUser(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PathVariable String username
    ) {
        long userId = jwtAuthentication.getUserId();

        FollowUserResult followUserResult = followUserUseCase.userIdFollowsUsername(userId, username);

        return new FollowUserResponse(
                followUserResult.getUsername(),
                followUserResult.getBio(),
                followUserResult.getImage(),
                followUserResult.isFollowing()
        );
    }
}
