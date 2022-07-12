package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.common.BaseControllerTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArticleQueryControllerTest {

    @Nested
    class 아티클_슬러그로_단건조회_테스트 extends BaseControllerTest {

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


    @Nested
    @Sql("classpath:sql/ArticleSearchCondTest.sql")
    class 아티클_목록조회_테스트 extends BaseControllerTest {

        String path = "/api/articles";

        //쿼리파라미터 없이
        @Test
        void 전체_조회() throws Exception {
            //setup
            인증("user3@user3.user3", "password");

            //when
            mockMvc.perform(get(path)
                            .header(AUTHORIZATION, token)
                            .contentType(APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("articles", hasSize(3)),
                            jsonPath("articleCount").value(3),

                            jsonPath("articles[0].title").value("article7title"),
                            jsonPath("articles[0].tagList", hasSize(1)),
                            jsonPath("articles[0].favorited").value(false),
                            jsonPath("articles[0].author.following").value(true),
                            jsonPath("articles[0].author.username").value("user1"),

                            jsonPath("articles[1].title").value("article8title"),
                            jsonPath("articles[1].tagList", hasSize(2)),
                            jsonPath("articles[1].favorited").value(false),
                            jsonPath("articles[1].author.following").value(true),
                            jsonPath("articles[1].author.username").value("user2"),

                            jsonPath("articles[2].title").value("article9title"),
                            jsonPath("articles[2].tagList", hasSize(3)),
                            jsonPath("articles[2].favorited").value(true),
                            jsonPath("articles[2].author.following").value(false),
                            jsonPath("articles[2].author.username").value("user3")
                    );
        }

        @Test
        void 태그로_필터링() throws Exception {
            인증("user3@user3.user3", "password");

            //when
            mockMvc.perform(get(path + "?tag=tag17")
                            .header(AUTHORIZATION, token)
                            .contentType(APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("articles", hasSize(2)),
                            jsonPath("articleCount").value(2),

                            jsonPath("articles[0].title").value("article8title"),
                            jsonPath("articles[0].tagList", hasSize(2)),
                            jsonPath("articles[0].favorited").value(false),
                            jsonPath("articles[0].author.following").value(true),
                            jsonPath("articles[0].author.username").value("user2"),

                            jsonPath("articles[1].title").value("article9title"),
                            jsonPath("articles[1].tagList", hasSize(3)),
                            jsonPath("articles[1].favorited").value(true),
                            jsonPath("articles[1].author.following").value(false),
                            jsonPath("articles[1].author.username").value("user3")
                    );
        }

        @Test
        void 작가로_필터링() throws Exception {
            인증("user3@user3.user3", "password");

            //when
            mockMvc.perform(get(path + "?author=user1")
                            .header(AUTHORIZATION, token)
                            .contentType(APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("articles", hasSize(1)),
                            jsonPath("articleCount").value(1),

                            jsonPath("articles[0].title").value("article7title"),
                            jsonPath("articles[0].tagList", hasSize(1)),
                            jsonPath("articles[0].favorited").value(false),
                            jsonPath("articles[0].author.following").value(true),
                            jsonPath("articles[0].author.username").value("user1")
                    );
        }

        @Test
        void 페이보릿으로_필터링() throws Exception {
            인증("user3@user3.user3", "password");

            //when
            mockMvc.perform(get(path + "?favorited=user2")
                            .header(AUTHORIZATION, token)
                            .contentType(APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("articles", hasSize(2)),
                            jsonPath("articleCount").value(2),

                            jsonPath("articles[0].title").value("article8title"),
                            jsonPath("articles[0].tagList", hasSize(2)),
                            jsonPath("articles[0].favorited").value(false),
                            jsonPath("articles[0].author.following").value(true),
                            jsonPath("articles[0].author.username").value("user2"),

                            jsonPath("articles[1].title").value("article9title"),
                            jsonPath("articles[1].tagList", hasSize(3)),
                            jsonPath("articles[1].favorited").value(true),
                            jsonPath("articles[1].author.following").value(false),
                            jsonPath("articles[1].author.username").value("user3")
                    );
        }


        @Test
        void 페이보릿과_태그로_필터링() throws Exception {
            인증("user3@user3.user3", "password");

            //when
            mockMvc.perform(get(path + "?favorited=user2&tag=tag18")
                            .header(AUTHORIZATION, token)
                            .contentType(APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("articles", hasSize(1)),
                            jsonPath("articleCount").value(1),

                            jsonPath("articles[0].title").value("article9title"),
                            jsonPath("articles[0].tagList", hasSize(3)),
                            jsonPath("articles[0].favorited").value(true),
                            jsonPath("articles[0].author.following").value(false),
                            jsonPath("articles[0].author.username").value("user3")
                    );
        }
    }

    @Sql("classpath:sql/ArticleSearchCondTest.sql")
    @Test
    void 팔로우중인_유저의_게시글_목록조회_테스트() {


    }

    @Nested
    @Sql("classpath:sql/ArticleSearchCondTest.sql")
    class 팔로우중인_유저의_게시글_목록조회_테스트 extends BaseControllerTest {

        @Test
        void 유저3_아티클7과8() throws Exception {

            인증("user3@user3.user3", "password");

            //when
            mockMvc.perform(get("/api/articles/feed")
                            .header(AUTHORIZATION, token)
                            .contentType(APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpectAll(
                            jsonPath("articles", hasSize(2)),
                            jsonPath("articleCount").value(2),

                            jsonPath("articles[0].title").value("article7title"),
                            jsonPath("articles[0].tagList", hasSize(1)),
                            jsonPath("articles[0].favorited").value(false),
                            jsonPath("articles[0].author.following").value(true),
                            jsonPath("articles[0].author.username").value("user1"),

                            jsonPath("articles[1].title").value("article8title"),
                            jsonPath("articles[1].tagList", hasSize(2)),
                            jsonPath("articles[1].favorited").value(false),
                            jsonPath("articles[1].author.following").value(true),
                            jsonPath("articles[1].author.username").value("user2")
                    );
        }
    }
}