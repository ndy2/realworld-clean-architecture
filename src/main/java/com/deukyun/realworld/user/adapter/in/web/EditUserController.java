package com.deukyun.realworld.user.adapter.in.web;


import com.deukyun.realworld.common.SecurityUser;
import com.deukyun.realworld.profile.application.port.in.EditProfileUseCase;
import com.deukyun.realworld.profile.application.port.in.dto.command.EditProfileCommand;
import com.deukyun.realworld.profile.application.port.in.dto.command.EditProfileResult;
import com.deukyun.realworld.user.adapter.in.dto.command.EditUserRequest;
import com.deukyun.realworld.user.adapter.in.dto.command.EditUserResponse;
import com.deukyun.realworld.user.application.port.in.EditUserUseCase;
import com.deukyun.realworld.user.application.port.in.dto.command.EditUserCommand;
import com.deukyun.realworld.user.application.port.in.dto.command.EditUserResult;
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
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody EditUserRequest editUserRequest
    ) {
        long userId = securityUser.getUserId();

        String email = editUserRequest.getEmail();
        String password = editUserRequest.getPassword();

        EditUserResult editUserResult = editUserUseCase.editUser(new EditUserCommand(userId, email, password));

        String username = editUserRequest.getUsername();
        String bio = editUserRequest.getBio();
        String image = editUserRequest.getImage();

        EditProfileResult editProfileResult = editProfileUseCase.editProfile(new EditProfileCommand(userId, username, bio, image));

        return new EditUserResponse(
                editUserResult.getEmail(),
                editProfileResult.getUsername(),
                editProfileResult.getBio(),
                editProfileResult.getImage()
        );
    }
}
