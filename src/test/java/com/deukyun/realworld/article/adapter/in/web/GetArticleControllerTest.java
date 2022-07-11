package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.common.BaseControllerTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetArticleControllerTest {

    @Nested
    class 아티클_단건조회_테스트 extends BaseControllerTest {

        String path = "/api/articles/{slug}";

        @Test
        void 자기글_조회() throws Exception {
            //setup
            사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
            인증("ndy@ndy.ndy", "ndyndy");
            프로필_업데이트(null, null, null, "haha.png", "I like to skateboard");
            아티클_등록("How to train your dragon",
                    "Ever wonder how?",
                    "It takes a Jacobian",
                    List.of("reactjs", "angularjs", "dragons"));

            //when
            mockMvc.perform(get(path, "how-to-train-your-dragon")
                            .header(AUTHORIZATION, token)
                            .contentType(APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("article.slug").value("how-to-train-your-dragon"),
                            jsonPath("article.title").value("How to train your dragon"),
                            jsonPath("article.description").value("Ever wonder how?"),
                            jsonPath("article.body").value("It takes a Jacobian"),
                            jsonPath("article.tagList", hasSize(3)),
                            jsonPath("article.tagList[0]").value("reactjs"),
                            jsonPath("article.tagList[1]").value("angularjs"),
                            jsonPath("article.tagList[2]").value("dragons"),
                            jsonPath("article.createdAt").exists(),
                            jsonPath("article.updatedAt").doesNotExist(),
                            jsonPath("article.favorited").value(false),
                            jsonPath("article.favoritesCount").value(0),
                            jsonPath("article.author.username").value("Ndy"),
                            jsonPath("article.author.bio").value("I like to skateboard"),
                            jsonPath("article.author.image").value("haha.png"),
                            jsonPath("article.author.following").value(false)
                    );
        }

        @Test
        void 팔로우_중인_남의_글_조회() throws Exception {
            //setup
            사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
            인증("ndy@ndy.ndy", "ndyndy");
            프로필_업데이트(null, null, null, "haha.png", "I like to skateboard");
            아티클_등록("How to train your dragon",
                    "Ever wonder how?",
                    "It takes a Jacobian",
                    List.of("reactjs", "angularjs", "dragons"));
            사용자_등록("Jakob", "jake@jake.jake", "jakejake");
            인증("jake@jake.jake", "jakejake");
            팔로우("Ndy");

            //when
            mockMvc.perform(get(path, "how-to-train-your-dragon")
                            .header(AUTHORIZATION, token)
                            .contentType(APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("article.slug").value("how-to-train-your-dragon"),
                            jsonPath("article.title").value("How to train your dragon"),
                            jsonPath("article.description").value("Ever wonder how?"),
                            jsonPath("article.body").value("It takes a Jacobian"),
                            jsonPath("article.tagList", hasSize(3)),
                            jsonPath("article.tagList[0]").value("reactjs"),
                            jsonPath("article.tagList[1]").value("angularjs"),
                            jsonPath("article.tagList[2]").value("dragons"),
                            jsonPath("article.createdAt").exists(),
                            jsonPath("article.updatedAt").doesNotExist(),
                            jsonPath("article.favorited").value(false),
                            jsonPath("article.favoritesCount").value(0),
                            jsonPath("article.author.username").value("Ndy"),
                            jsonPath("article.author.bio").value("I like to skateboard"),
                            jsonPath("article.author.image").value("haha.png"),
                            jsonPath("article.author.following").value(true)
                    );
        }

        @Test
        void 페이보릿_추가한_글_조회() throws Exception {
            //setup
            사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
            인증("ndy@ndy.ndy", "ndyndy");
            프로필_업데이트(null, null, null, "haha.png", "I like to skateboard");
            아티클_등록("How to train your dragon",
                    "Ever wonder how?",
                    "It takes a Jacobian",
                    List.of("reactjs", "angularjs", "dragons"));
            페이보릿_추가("how-to-train-your-dragon");

            사용자_등록("Jakob", "jake@jake.jake", "jakejake");
            인증("jake@jake.jake", "jakejake");
            페이보릿_추가("how-to-train-your-dragon");

            //when
            mockMvc.perform(get(path, "how-to-train-your-dragon")
                            .header(AUTHORIZATION, token)
                            .contentType(APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("article.slug").value("how-to-train-your-dragon"),
                            jsonPath("article.title").value("How to train your dragon"),
                            jsonPath("article.description").value("Ever wonder how?"),
                            jsonPath("article.body").value("It takes a Jacobian"),
                            jsonPath("article.tagList", hasSize(3)),
                            jsonPath("article.tagList[0]").value("reactjs"),
                            jsonPath("article.tagList[1]").value("angularjs"),
                            jsonPath("article.tagList[2]").value("dragons"),
                            jsonPath("article.createdAt").exists(),
                            jsonPath("article.updatedAt").doesNotExist(),
                            jsonPath("article.favorited").value(true),
                            jsonPath("article.favoritesCount").value(2),
                            jsonPath("article.author.username").value("Ndy"),
                            jsonPath("article.author.bio").value("I like to skateboard"),
                            jsonPath("article.author.image").value("haha.png"),
                            jsonPath("article.author.following").value(false)
                    );

        }

        @Test
        void 로그아웃_하고_조회() throws Exception {
            //setup
            사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
            인증("ndy@ndy.ndy", "ndyndy");
            프로필_업데이트(null, null, null, "haha.png", "I like to skateboard");
            아티클_등록("How to train your dragon",
                    "Ever wonder how?",
                    "It takes a Jacobian",
                    List.of("reactjs", "angularjs", "dragons"));
            페이보릿_추가("how-to-train-your-dragon");

            사용자_등록("Jakob", "jake@jake.jake", "jakejake");
            인증("jake@jake.jake", "jakejake");
            페이보릿_추가("how-to-train-your-dragon");


            //when
            mockMvc.perform(get(path, "how-to-train-your-dragon")
                            // 로그아웃
                            /*.header(AUTHORIZATION, token)*/
                            .contentType(APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("article.slug").value("how-to-train-your-dragon"),
                            jsonPath("article.title").value("How to train your dragon"),
                            jsonPath("article.description").value("Ever wonder how?"),
                            jsonPath("article.body").value("It takes a Jacobian"),
                            jsonPath("article.tagList", hasSize(3)),
                            jsonPath("article.tagList[0]").value("reactjs"),
                            jsonPath("article.tagList[1]").value("angularjs"),
                            jsonPath("article.tagList[2]").value("dragons"),
                            jsonPath("article.createdAt").exists(),
                            jsonPath("article.updatedAt").doesNotExist(),
                            jsonPath("article.favorited").value(false),
                            jsonPath("article.favoritesCount").value(2),
                            jsonPath("article.author.username").value("Ndy"),
                            jsonPath("article.author.bio").value("I like to skateboard"),
                            jsonPath("article.author.image").value("haha.png"),
                            jsonPath("article.author.following").value(false)
                    );
        }

    }
}