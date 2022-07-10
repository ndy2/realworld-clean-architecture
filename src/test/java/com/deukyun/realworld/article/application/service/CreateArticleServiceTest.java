package com.deukyun.realworld.article.application.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateArticleServiceTest {

    /**
     * 복붙으로 가져옴
     */
    private String createSlug(String title) {

        return String.join("-", title.toLowerCase().split(" "));
    }

    @Test
    void 타이틀로_슬러그만드는_로직_테스트() {
        //given
        String title = "How to train your dragon";

        //when
        String slug = createSlug(title);

        //then
        assertThat(slug).isEqualTo("how-to-train-your-dragon");
    }
}