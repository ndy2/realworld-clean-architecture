package com.deukyun.realworld.article.application.port.out;

import java.util.List;

public interface FindArticlesByFieldsPort {

    List<FindArticleResult> findArticlesByFields(FindArticlesByFieldsQuery command);
}
