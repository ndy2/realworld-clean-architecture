package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.common.BaseControllerTest;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthenticationControllerTest extends BaseControllerTest {

    @Test
    void 인증_테스트() throws Exception {
        //setup
        사용자_등록("Jakob", "jake@jake.jake", "jakejake");

        //given
        ObjectNode authenticationRequest = objectMapper.createObjectNode();
        ObjectNode user = authenticationRequest.putObject("user");
        user.put("email", "jake@jake.jake");
        user.put("password", "jakejake");

        //when
        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(authenticationRequest)))
                //then
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("user.email").value("Jacob"),
                        jsonPath("user.token").value("jake@jake.jake"),
                        jsonPath("user.username").value("Jacob"),
                        jsonPath("user.bio").value("null"),
                        jsonPath("user.image").value("null")
                );


    }
}