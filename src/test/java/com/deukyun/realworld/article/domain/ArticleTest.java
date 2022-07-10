package com.deukyun.realworld.article.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {

    @Test
    void 아티클_생성() {
        //given
        String title = "How to train your dragon";
        String body = "You have to believe";
        String description = "Ever wonder how?";
        Tags tags = new Tags(List.of("reactjs", "angularjs", "dragons"));

        //when
        Article article = new Article(title, description, body, tags);

        //then
        assertThat(article).isNotNull();
    }
}