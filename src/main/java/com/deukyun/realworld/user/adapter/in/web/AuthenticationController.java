package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.common.SecurityUser;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdQuery;
import com.deukyun.realworld.profile.application.port.in.GetProfileByUserIdResult;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        Email email = authenticationRequest.getEmail();
        Password password = authenticationRequest.getPassword();

        //토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        UsernamePasswordAuthenticationToken resultToken = (UsernamePasswordAuthenticationToken) authenticationManager.authenticate(authenticationToken);
        //컨텍스트 홀더에 저장
        SecurityContextHolder.getContext().setAuthentication(resultToken);

        //principal 에서 사용자 아이디와 토큰 조회
        SecurityUser principal = (SecurityUser) resultToken.getPrincipal();
        GetProfileByUserIdResult profileResponse
                = getProfileByUserIdQuery.getProfileByUserId(principal.getUserId());

        return new AuthenticationResponse(
                email,
                principal.getToken(),
                profileResponse.getUsername(),
                profileResponse.getBio(),
                profileResponse.getImage()
        );
    }
}
