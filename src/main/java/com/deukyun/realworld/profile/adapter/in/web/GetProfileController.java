package com.deukyun.realworld.profile.adapter.in.web;

import com.deukyun.realworld.common.SecurityUser;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUsernameQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUsernameResult;
import com.deukyun.realworld.user.domain.User.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetProfileController {

    private final GetProfileByUsernameQuery getProfileByUsernameQuery;

    @GetMapping("/api/profiles/{username}")
    public GetProfileResponse getProfile(
            @AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable String username
    ) {
        GetProfileByUsernameResult queryResult
                = getProfileByUsernameQuery.getProfileByUsername(
                        new UserId(securityUser.getUserId()), username
        );

        return new GetProfileResponse(
                queryResult.getUsername(),
                queryResult.getBio(),
                queryResult.getImage(),
                queryResult.isFollow()
        );
    }
}
