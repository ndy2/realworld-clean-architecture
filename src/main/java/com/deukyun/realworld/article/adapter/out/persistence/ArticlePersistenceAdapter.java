package com.deukyun.realworld.article.adapter.out.persistence;

import com.deukyun.realworld.article.adapter.out.persistence.ArticleJpaEntity.ArticleTagJpaEntity;
import com.deukyun.realworld.article.adapter.out.persistence.repository.ArticleRepository;
import com.deukyun.realworld.article.adapter.out.persistence.repository.ArticleSearchCond;
import com.deukyun.realworld.article.adapter.out.persistence.repository.TagRepository;
import com.deukyun.realworld.article.application.port.out.*;
import com.deukyun.realworld.common.component.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;


@RequiredArgsConstructor
@PersistenceAdapter
class ArticlePersistenceAdapter implements
        InsertArticlePort,
        FindArticlesByFieldsPort,
        FindArticleBySlugPort,
        FindFeedArticlesPort {

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
    public List<FindArticleResult> findArticlesByFields(FindArticlesByFieldsQuery query) {

        List<ArticleJpaEntity> articles = articleRepository.searchArticle(
                new ArticleSearchCond(
                        query.getTag(),
                        query.getAuthor(),
                        query.getFavorited()),
                query.getOffset(),
                query.getLimit()
        );

        return articles.stream().map(this::toResult).collect(toList());
    }

    @Override
    public FindArticleResult findArticleBySlug(String slug) {

        return toResult(articleRepository.findBySlug(slug).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<FindArticleResult> findFeedArticles(FindFeedArticleQuery query) {
        //팔로우중인 사용자의 아티클 조회
        List<ArticleJpaEntity> articles = articleRepository.feedArticles(
                query.getUserId().getValue(),
                query.getOffset(),
                query.getLimit()
        );

        return articles.stream().map(this::toResult).collect(toList());
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
}
