package com.deukyun.realworld.profile.adapter.in.web;

import com.deukyun.realworld.common.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetProfileControllerTest extends BaseControllerTest {

    @Test
    void 프로필_조회_테스트() throws Exception {
        //given
        사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
        인증("ndy@ndy.ndy", "ndyndy");
        프로필_업데이트(null, null, null, "haha.png", "I like to skateboard");

        //when
        mockMvc.perform(get("/api/profiles/{username}", "Ndy")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("profile.username").value("Ndy"),
                        jsonPath("profile.image").value("haha.png"),
                        jsonPath("profile.bio").value("I like to skateboard"),
                        jsonPath("profile.following").exists()
                );
    }
}