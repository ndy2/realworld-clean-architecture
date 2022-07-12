package com.deukyun.realworld.article.application.port.in;

import com.deukyun.realworld.article.application.port.in.dto.command.CreateArticleCommand;
import com.deukyun.realworld.article.application.port.in.dto.command.CreateArticleResult;

public interface CreateArticleUseCase {

    CreateArticleResult createArticle(CreateArticleCommand createArticleCommand);
}
