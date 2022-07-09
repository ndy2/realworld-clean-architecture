package com.deukyun.realworld.user.adapter.in.web;


import com.deukyun.realworld.profile.application.port.in.EditProfileUseCase;
import com.deukyun.realworld.user.application.port.in.EditUserResponse;
import com.deukyun.realworld.user.application.port.in.EditUserUseCase;
import lombok.RequiredArgsConstructor;
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
            @RequestBody EditUserRequest editUserRequest
    ) {

        return null;
    }
}
