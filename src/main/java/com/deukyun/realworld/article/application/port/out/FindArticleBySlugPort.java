package com.deukyun.realworld.article.application.port.out;

import com.deukyun.realworld.article.application.port.out.dto.query.FindArticleResult;

public interface FindArticleBySlugPort {

    FindArticleResult findArticleBySlug(String slug);
}
