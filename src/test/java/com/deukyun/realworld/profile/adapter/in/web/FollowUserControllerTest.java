package com.deukyun.realworld.profile.adapter.in.web;

import com.deukyun.realworld.common.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FollowUserControllerTest extends BaseControllerTest {

    @Test
    void 팔로우_테스트() throws Exception {
        //given
        사용자_등록("Jakob", "jake@jake.jake", "jakejake");
        인증("jake@jake.jake", "jakejake");
        프로필_업데이트(null, null, null, "haha.png", "I like to skateboard");

        사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
        인증("ndy@ndy.ndy", "ndyndy");

        //when
        mockMvc.perform(post("/api/profiles/{username}/follow", "Jakob")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("profile.username").value("Jakob"),
                        jsonPath("profile.bio").value("I like to skateboard"),
                        jsonPath("profile.image").value("haha.png"),
                        jsonPath("profile.following").value(true)
                );
    }

    @Test
    void 언팔로우_테스트() throws Exception {
        //given
        사용자_등록("Jakob", "jake@jake.jake", "jakejake");
        인증("jake@jake.jake", "jakejake");

        사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
        인증("ndy@ndy.ndy", "ndyndy");
        팔로우("Jakob");

        //when
        mockMvc.perform(delete("/api/profiles/{username}/follow", "Jakob")
                        .header(AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("profile.username").value("Jakob"),
                        jsonPath("profile.bio").doesNotExist(),
                        jsonPath("profile.image").doesNotExist(),
                        jsonPath("profile.following").value(false)
                );
    }
}