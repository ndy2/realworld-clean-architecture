package com.deukyun.realworld.article.application.port.in;

import com.deukyun.realworld.article.application.port.in.dto.command.UpdateArticleCommand;
import com.deukyun.realworld.article.application.port.in.dto.command.UpdateArticleResult;

public interface UpdateArticleUseCase {

    UpdateArticleResult updateArticle(UpdateArticleCommand command);
}
