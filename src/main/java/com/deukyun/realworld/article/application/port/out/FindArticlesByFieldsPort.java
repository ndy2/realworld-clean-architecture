package com.deukyun.realworld.article.application.port.out;

import com.deukyun.realworld.article.application.port.out.dto.query.FindArticleResult;
import com.deukyun.realworld.article.application.port.out.dto.query.FindArticlesByFieldsQuery;

import java.util.List;

public interface FindArticlesByFieldsPort {

    List<FindArticleResult> findArticlesByFields(FindArticlesByFieldsQuery command);
}
