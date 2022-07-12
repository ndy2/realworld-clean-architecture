package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.common.SecurityUser;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdResult;
import com.deukyun.realworld.user.domain.User.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetCurrentUserController {

    private final GetProfileByUserIdQuery getProfileByUserIdQuery;

    @GetMapping("/api/user")
    public GetCurrentUserResponse getCurrentUser(
            @AuthenticationPrincipal SecurityUser securityUser
    ) {
        UserId userId = new UserId(securityUser.getUserId());
        String token = securityUser.getToken();

        GetProfileByUserIdResult profileResult = getProfileByUserIdQuery.getProfileByUserId(userId);

        return new GetCurrentUserResponse(
                profileResult.getEmail(),
                token,
                profileResult.getUsername(),
                profileResult.getBio(),
                profileResult.getImage()
        );
    }
}
