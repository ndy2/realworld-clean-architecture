package com.deukyun.realworld.article.application.port.out;

import com.deukyun.realworld.article.application.port.out.dto.command.InsertArticleCommand;
import com.deukyun.realworld.article.application.port.out.dto.command.InsertArticleResult;

public interface InsertArticlePort {

    InsertArticleResult insertArticle(InsertArticleCommand insertArticleCommand);
}
