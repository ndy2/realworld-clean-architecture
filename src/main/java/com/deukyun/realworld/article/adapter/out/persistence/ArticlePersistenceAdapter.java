package com.deukyun.realworld.article.adapter.out.persistence;

import com.deukyun.realworld.article.adapter.out.persistence.ArticleJpaEntity.ArticleTagJpaEntity;
import com.deukyun.realworld.article.adapter.out.persistence.repository.ArticleRepository;
import com.deukyun.realworld.article.adapter.out.persistence.repository.ArticleSearchCond;
import com.deukyun.realworld.article.adapter.out.persistence.repository.TagRepository;
import com.deukyun.realworld.article.application.port.out.*;
import com.deukyun.realworld.article.application.port.out.dto.command.*;
import com.deukyun.realworld.article.application.port.out.dto.query.FindArticleResult;
import com.deukyun.realworld.article.application.port.out.dto.query.FindArticlesByFieldsQuery;
import com.deukyun.realworld.article.application.port.out.dto.query.FindAuthorResult;
import com.deukyun.realworld.article.application.port.out.dto.query.FindFeedArticleQuery;
import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.common.exception.RealworldRuntimeException;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;


@RequiredArgsConstructor
@PersistenceAdapter
class ArticlePersistenceAdapter implements
        InsertArticlePort,
        FindArticlesByFieldsPort,
        FindArticleBySlugPort,
        FindFeedArticlesPort,
        UpdateArticlePort,
        DeleteArticlePort {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    @Override
    public InsertArticleResult insertArticle(InsertArticleCommand insertArticleCommand) {
        /*
          태그 처리
          이미 있던 태그를 필터링 하고
          새롭게 추가된 태그에 대해서만 태그 삽입
         */
        List<String> allTagNames = insertArticleCommand.getTags();

        List<TagJpaEntity> allTags = allTagNames.stream().map(TagJpaEntity::new).collect(toList());
        List<TagJpaEntity> alreadyExistingTags = tagRepository.findByNameIn(allTagNames);

        List<TagJpaEntity> newTags = getNewTags(allTags, alreadyExistingTags);
        tagRepository.saveAll(newTags);

        //게시글 삽입
        ArticleJpaEntity article = new ArticleJpaEntity(
                insertArticleCommand.getSlug(),
                insertArticleCommand.getTitle(),
                insertArticleCommand.getBody(),
                insertArticleCommand.getDescription(),
                insertArticleCommand.getAuthorProfileId()
        );
        alreadyExistingTags.forEach(tag -> article.addArticleTag(new ArticleTagJpaEntity(tag)));
        newTags.forEach(tag -> article.addArticleTag(new ArticleTagJpaEntity(tag)));

        articleRepository.save(article);

        return new InsertArticleResult(article.getCreatedAt());
    }

    private List<TagJpaEntity> getNewTags(List<TagJpaEntity> allTags, List<TagJpaEntity> existingTags) {
        return allTags.stream()
                .filter(tag -> existingTags.stream()
                        .filter(tag::isEqual)
                        .findAny()
                        .isEmpty()
                )
                .collect(toList());
    }

    @Override
    public List<FindArticleResult> findArticlesByFields(FindArticlesByFieldsQuery command) {

        List<ArticleJpaEntity> articles = articleRepository.searchArticle(
                new ArticleSearchCond(command.getTag(), command.getAuthor(), command.getFavorited()),
                command.getOffset(), command.getLimit()
        );

        return articles.stream().map(this::toResult).collect(toList());
    }

    @Override
    public FindArticleResult findArticleBySlug(String slug) {

        return toResult(articleRepository.findBySlug(slug).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<FindArticleResult> findFeedArticles(FindFeedArticleQuery command) {
        //팔로우중인 사용자의 아티클 조회
        List<ArticleJpaEntity> articles = articleRepository.feedArticles(
                command.getUserId(),
                command.getOffset(),
                command.getLimit()
        );

        return articles.stream().map(this::toResult).collect(toList());
    }

    @Override
    public UpdateArticleStateResult updateArticle(UpdateArticleStateCommand command) {
        long userId = command.getUserId();
        String origSlug = command.getOrigSlug();

        // userId 조인으로 다른 사람에 대한 요청 필터링
        articleRepository.findByUserIdAndSlug(userId, origSlug).orElseThrow(RealworldRuntimeException::new);

        // 깁게 조인 해옴
        ArticleJpaEntity article = articleRepository.findBySlug(origSlug).orElseThrow(IllegalStateException::new);

        article.update(
                command.getTitle(),
                command.getUpdateSlug(),
                command.getBody()
        );

        return toUpdateResult(article);
    }

    @Override
    public void deleteArticle(long userId, String slug) {
        ArticleJpaEntity article = articleRepository.findByUserIdAndSlug(userId, slug).orElseThrow(RealworldRuntimeException::new);

        articleRepository.delete(article);
    }

    private FindArticleResult toResult(ArticleJpaEntity article) {
        return new FindArticleResult(
                article.getId(),
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                new FindAuthorResult(
                        article.getAuthorProfileId(),
                        article.getAuthorUsername(),
                        article.getAuthorBio(),
                        article.getAuthorImage()
                )
        );
    }

    private UpdateArticleStateResult toUpdateResult(ArticleJpaEntity article) {
        return new UpdateArticleStateResult(
                article.getId(),
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                new CommandAuthorResult(
                        article.getAuthorProfileId(),
                        article.getAuthorUsername(),
                        article.getAuthorBio(),
                        article.getAuthorImage()
                )
        );
    }
}
