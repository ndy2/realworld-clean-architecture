package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.user.application.port.in.RegisterUserCommand;
import com.deukyun.realworld.user.application.port.in.RegisterUserUseCase;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegisterUserController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/api/users")
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {

        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();
        String username = registerUserRequest.getUsername();

        registerUserUseCase.registerUser(
                new RegisterUserCommand(
                        new Email(email),
                        new Password(password),
                        username
                )
        );

        return new RegisterUserResponse(email, username);
    }
}
