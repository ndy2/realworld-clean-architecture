package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.configuration.jwt.JwtAuthenticationToken.JwtAuthentication;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdResult;
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
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        long userId = jwtAuthentication.getUserId();
        String token = jwtAuthentication.getToken();

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
