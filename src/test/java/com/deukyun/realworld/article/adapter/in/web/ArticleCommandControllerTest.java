package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.common.BaseControllerTest;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArticleCommandControllerTest extends BaseControllerTest {

    @Test
    void 아티클_등록_테스트() throws Exception {

        //setup
        사용자_등록("Ndy", "ndy@ndy.ndy", "ndyndy");
        인증("ndy@ndy.ndy", "ndyndy");
        프로필_업데이트(null, null, null, "haha.png", "I like to skateboard");

        //given
        ObjectNode createArticleRequest = objectMapper.createObjectNode();
        ObjectNode article = createArticleRequest.putObject("article");
        article.put("title", "How to train your dragon");
        article.put("description", "Ever wonder how?");
        article.put("body", "It takes a Jacobian");
        article.putArray("tagList").add("reactjs").add("angularjs").add("dragons");

        mockMvc.perform(post("/api/articles")
                        .header(AUTHORIZATION, token)
                        .contentType(APPLICATION_JSON)
                        .content(createJson(createArticleRequest)))
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
                        jsonPath("article.author.username").value("Ndy"),
                        jsonPath("article.author.bio").value("I like to skateboard"),
                        jsonPath("article.author.image").value("haha.png")
                );
    }
}