package com.deukyun.realworld.article.application.port.out;

import com.deukyun.realworld.article.application.port.out.dto.command.UpdateArticleStateCommand;
import com.deukyun.realworld.article.application.port.out.dto.command.UpdateArticleStateResult;

public interface UpdateArticlePort {

    UpdateArticleStateResult updateArticle(UpdateArticleStateCommand command);
}
