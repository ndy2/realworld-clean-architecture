package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.configuration.jwt.JwtAuthenticationToken;
import com.deukyun.realworld.configuration.jwt.JwtAuthenticationToken.JwtAuthentication;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final GetProfileByUserIdQuery getProfileByUserIdQuery;

    @PostMapping("/api/users/login")
    public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        //토큰 생성
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(email, password);
        JwtAuthenticationToken resultToken = (JwtAuthenticationToken) authenticationManager.authenticate(jwtAuthenticationToken);
        //컨텍스트 홀더에 저장
        SecurityContextHolder.getContext().setAuthentication(resultToken);

        //principal 에서 사용자 아이디와 토큰 조회
        JwtAuthentication principal = (JwtAuthentication) resultToken.getPrincipal();
        GetProfileByUserIdResult profileResponse = getProfileByUserIdQuery.getProfileByUserId(principal.getUserId());

        return new AuthenticationResponse(
                email,
                principal.getToken(),
                profileResponse.getUsername(),
                profileResponse.getBio(),
                profileResponse.getImage()
        );
    }
}
