package com.deukyun.realworld.article.adapter.out.persistence;

import com.deukyun.realworld.article.adapter.out.persistence.ArticleJpaEntity.ArticleTagJpaEntity;
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
        FindArticleBySlugPort {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    @Override
    public InsertArticleResult insertArticle(InsertArticleCommand insertArticleCommand) {
        /**
         * 태그 처리
         * 이미 있던 태그를 필터링 하고
         * 새롭게 추가된 태그에 대해서만 태그 삽입
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
    public List<FindArticleResult> findArticlesByFields(FindArticlesByFieldsCommand command) {
        //TODO - 동적 where 절 binding


        return null;
    }

    @Override
    public FindArticleResult findArticleBySlug(String slug) {
        ArticleJpaEntity article = articleRepository.findBySlug(slug).orElseThrow(IllegalArgumentException::new);

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
