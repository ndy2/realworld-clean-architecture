package com.deukyun.realworld.user.adapter.in.web;


import com.deukyun.realworld.infrastructure.security.jwt.domain.JwtAuthenticationToken;
import com.deukyun.realworld.profile.application.port.in.EditProfileCommand;
import com.deukyun.realworld.profile.application.port.in.EditProfileUseCase;
import com.deukyun.realworld.user.application.port.in.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.EditUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EditUserController {

    private final EditUserUseCase editUserUseCase;
    private final EditProfileUseCase editProfileUseCase;

    @PutMapping("/api/users")
    public EditUserResponse editUser(
            @AuthenticationPrincipal JwtAuthenticationToken jwtAuthenticationToken,
            @RequestBody EditUserRequest editUserRequest
    ) {
        long userId = jwtAuthenticationToken.getId();
        String token = jwtAuthenticationToken.getJwtString();

        String email = editUserRequest.getEmail();
        String password = editUserRequest.getPassword();

        editUserUseCase.editUser(new EditUserCommand(userId, email, password));

        String username = editUserRequest.getUsername();
        String bio = editUserRequest.getBio();
        String image = editUserRequest.getImage();

        editProfileUseCase.editProfile(new EditProfileCommand(userId, username, bio, image));

        return new EditUserResponse(email, token, username, bio, image);
    }
}
