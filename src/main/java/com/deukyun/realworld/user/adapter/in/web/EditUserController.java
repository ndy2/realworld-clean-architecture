package com.deukyun.realworld.user.adapter.in.web;


import com.deukyun.realworld.infrastructure.security.jwt.JwtAuthenticationToken.JwtAuthentication;
import com.deukyun.realworld.profile.application.port.in.EditProfileCommand;
import com.deukyun.realworld.profile.application.port.in.EditProfileResult;
import com.deukyun.realworld.profile.application.port.in.EditProfileUseCase;
import com.deukyun.realworld.user.application.port.in.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.EditUserResult;
import com.deukyun.realworld.user.application.port.in.EditUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EditUserController {

    private final EditUserUseCase editUserUseCase;
    private final EditProfileUseCase editProfileUseCase;

    @PatchMapping("/api/users")
    public EditUserResponse editUser(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody EditUserRequest editUserRequest
    ) {
        long userId = jwtAuthentication.getUserId();
        String token = jwtAuthentication.getToken();

        String email = editUserRequest.getEmail();
        String password = editUserRequest.getPassword();

        EditUserResult editUserResult = editUserUseCase.editUser(new EditUserCommand(userId, email, password));

        String username = editUserRequest.getUsername();
        String bio = editUserRequest.getBio();
        String image = editUserRequest.getImage();

        EditProfileResult editProfileResult = editProfileUseCase.editProfile(new EditProfileCommand(userId, username, bio, image));

        return new EditUserResponse(
                editUserResult.getEmail(),
                token,
                editProfileResult.getUsername(),
                editProfileResult.getBio(),
                editProfileResult.getImage()
        );
    }
}
