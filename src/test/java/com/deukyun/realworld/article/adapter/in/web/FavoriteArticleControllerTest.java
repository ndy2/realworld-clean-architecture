package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.common.BaseControllerTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FavoriteArticleControllerTest extends BaseControllerTest {

    @Test
    void 페이보릿_추가_테스트() throws Exception {

        //setup
        사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
        인증("ndy@ndy.ndy", "ndyndy");
        프로필_업데이트(null, null, null, "haha.png", "I like to skateboard");
        아티클_등록("How to train your dragon",
                "Ever wonder how?",
                "It takes a Jacobian",
                List.of("reactjs", "angularjs", "dragons"));

        //when
        mockMvc.perform(post("/api/articles/{slug}/favorite", "how-to-train-your-dragon")
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
                        jsonPath("article.favoritesCount").value(1),
                        jsonPath("article.author.username").value("Ndy"),
                        jsonPath("article.author.bio").value("I like to skateboard"),
                        jsonPath("article.author.image").value("haha.png"),
                        jsonPath("article.author.following").value(false)
                );
    }

    @Test
    void 페이보릿_제거_테스트() throws Exception {

        //setup
        사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
        인증("ndy@ndy.ndy", "ndyndy");
        프로필_업데이트(null, null, null, "haha.png", "I like to skateboard");
        아티클_등록("How to train your dragon",
                "Ever wonder how?",
                "It takes a Jacobian",
                List.of("reactjs", "angularjs", "dragons"));
        페이보릿_추가("how-to-train-your-dragon");

        //when
        mockMvc.perform(delete("/api/articles/{slug}/unfavorite", "how-to-train-your-dragon")
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
}