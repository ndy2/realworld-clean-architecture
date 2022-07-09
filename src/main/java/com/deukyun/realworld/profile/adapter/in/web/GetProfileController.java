package com.deukyun.realworld.profile.adapter.in.web;

import com.deukyun.realworld.profile.application.port.in.GetProfileByUsernameQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUsernameResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetProfileController {

    private final GetProfileByUsernameQuery getProfileByUsernameQuery;

    @GetMapping("/api/profiles/{username}")
    public GetProfileResponse getProfile(
            @PathVariable String username
    ) {
        GetProfileByUsernameResult queryResult = getProfileByUsernameQuery.getProfileByUsername(username);

        return new GetProfileResponse(
                queryResult.getUsername(),
                queryResult.getBio(),
                queryResult.getImage(),

                //TODO following 구현
                false
        );
    }
}
