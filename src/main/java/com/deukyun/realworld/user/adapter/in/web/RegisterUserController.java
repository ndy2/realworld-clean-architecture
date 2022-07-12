package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.user.application.port.in.RegisterUserCommand;
import com.deukyun.realworld.user.application.port.in.RegisterUserUseCase;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegisterUserController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/api/users")
    public RegisterUserResponse registerUser(
            @RequestBody RegisterUserRequest registerUserRequest
    ) {
        Email email = registerUserRequest.getEmail();
        Password password = registerUserRequest.getPassword();
        String username = registerUserRequest.getUsername();

        registerUserUseCase.registerUser(
                new RegisterUserCommand(
                        email,
                        password,
                        username
                )
        );

        return new RegisterUserResponse(email, username);
    }
}
