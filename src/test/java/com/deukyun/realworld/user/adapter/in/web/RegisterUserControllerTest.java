package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.common.BaseControllerTest;
import com.deukyun.realworld.user.application.port.in.RegisterUserCommand;
import com.deukyun.realworld.user.application.port.in.RegisterUserUseCase;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegisterUserControllerTest extends BaseControllerTest {

    @MockBean
    RegisterUserUseCase registerUserUseCase;

    @Test
    void 회원_가입() throws Exception {

        //given
        ObjectNode registerUserRequest = objectMapper.createObjectNode();
        ObjectNode user = registerUserRequest.putObject("user");
        user.put("username", "Jacob");
        user.put("email", "jake@jake.jake");
        user.put("password", "jakejake");

        //when
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(registerUserRequest)))
                //then
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("user.username").value("Jacob"),
                        jsonPath("user.email").value("jake@jake.jake")
                );

        verify(registerUserUseCase).registerUser(
                new RegisterUserCommand(
                        new Email("jake@jake.jake"),
                        new Password("jakejake"),
                        "Jacob"
                )
        );
    }
}