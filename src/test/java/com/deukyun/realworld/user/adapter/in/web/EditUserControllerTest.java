package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.common.BaseControllerTest;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EditUserControllerTest extends BaseControllerTest {

    @Test
    void 사용자_수정_테스트() throws Exception {
        //setup
        사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
        인증("ndy@ndy.ndy", "ndyndy");

        //given
        ObjectNode editUserRequest = objectMapper.createObjectNode();
        ObjectNode user = editUserRequest.putObject("user");
        user.put("email", "jake@jake.jake");
        user.put("bio", "I like to skateboard");
        user.put("image", "https://i.stack.imgur.com/xHWG8.jpg");

        //when
        mockMvc.perform(patch("/api/users")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJson(editUserRequest)))
                //then
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("user.email").value("jake@jake.jake"),
                        jsonPath("user.username").value("Ndy"),
                        jsonPath("user.bio").value("I like to skateboard"),
                        jsonPath("user.image").value("https://i.stack.imgur.com/xHWG8.jpg")
                );
    }
}