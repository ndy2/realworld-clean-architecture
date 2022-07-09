package com.deukyun.realworld.user.adapter.in.web;

import com.deukyun.realworld.common.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetCurrentUserControllerTest extends BaseControllerTest {

    @Test
    void 현재_사용자_조회_테스트() throws Exception {

        사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
        인증("ndy@ndy.ndy", "ndyndy");

        //when
        mockMvc.perform(get("/api/user")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("user.email").value("ndy@ndy.ndy"),
                        jsonPath("user.token").value(token),
                        jsonPath("user.username").value("Ndy"),
                        jsonPath("user.bio").doesNotExist(),
                        jsonPath("user.image").doesNotExist()
                );
    }
}