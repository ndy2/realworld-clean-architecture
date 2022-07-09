package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.infrastructure.security.jwt.domain.JwtAuthenticationToken;
import com.deukyun.realworld.profile.application.port.in.GetProfileQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final GetProfileQuery getProfileQuery;

    @PostMapping("/api/users/login")
    public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        //토큰 생성
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(email, password);
        JwtAuthenticationToken resultToken = (JwtAuthenticationToken) authenticationManager.authenticate(jwtAuthenticationToken);

        GetProfileResult profileResponse = getProfileQuery.getByUserId(resultToken.getId());

        return new AuthenticationResponse(
                email,
                resultToken.getJwtString(),
                profileResponse.getUsername(),
                profileResponse.getBio(),
                profileResponse.getImage()
        );
    }
}
