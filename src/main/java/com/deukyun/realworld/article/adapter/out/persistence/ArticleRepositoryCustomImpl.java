package com.deukyun.realworld.article.adapter.out.persistence;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

import static com.deukyun.realworld.article.adapter.out.persistence.QArticleJpaEntity.articleJpaEntity;
import static com.deukyun.realworld.article.adapter.out.persistence.QArticleJpaEntity_ArticleTagJpaEntity.articleTagJpaEntity;
import static com.deukyun.realworld.article.adapter.out.persistence.QTagJpaEntity.tagJpaEntity;
import static com.deukyun.realworld.favorite.adapter.out.persistence.QFavoriteJpaEntity.favoriteJpaEntity;
import static com.deukyun.realworld.profile.adapter.out.persistence.QProfileJpaEntity.profileJpaEntity;
import static com.deukyun.realworld.user.adapter.out.persistence.QUserJpaEntity.userJpaEntity;
import static com.querydsl.jpa.JPAExpressions.select;
import static com.querydsl.jpa.JPAExpressions.selectFrom;

@Repository
public class ArticleRepositoryCustomImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory query;

    public ArticleRepositoryCustomImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<ArticleJpaEntity> searchArticle(ArticleSearchCond cond, long offset, long limit) {

        String tag = cond.getTag();
        String authorName = cond.getAuthor();
        String favoritedUsername = cond.getFavorited();

        List<ArticleJpaEntity> articles = query
                .selectFrom(articleJpaEntity)
                .distinct()
                .join(articleJpaEntity.authorProfile, profileJpaEntity).fetchJoin()
                .where(
                        containsTag(tag),
                        writtenBy(authorName),
                        favorited(favoritedUsername)
                )
                .offset(offset)
                .limit(limit)
                .fetch();

        //Lazy Loading : article -> article tag -> tag
        for (ArticleJpaEntity article : articles) {
            article.getTagList();
        }

        return articles;
    }

    private BooleanBuilder containsTag(String tag) {

        return nullSafeBuilder(() -> articleJpaEntity.in(
                select(articleTagJpaEntity.article)
                        .from(articleTagJpaEntity)
                        .where(articleTagJpaEntity.tag.in(
                                select(tagJpaEntity)
                                        .from(tagJpaEntity)
                                        .where(tagJpaEntity.name.eq(tag))
                        ))
        ));
    }

    private BooleanBuilder writtenBy(String authorName) {

        return nullSafeBuilder(() -> articleJpaEntity.authorProfile.eq(
                selectFrom(profileJpaEntity)
                        .where(profileJpaEntity.username.eq(authorName))
        ));
    }

    private BooleanBuilder favorited(String username) {
        return nullSafeBuilder(() -> articleJpaEntity.id.in(
                //유저 아이디로 해당 유저의 페이보릿 아티클 아이디 목록 조회
                select(favoriteJpaEntity.articleId)
                        .from(favoriteJpaEntity)
                        .where(favoriteJpaEntity.userId.eq(
                                //유저 이름으로 유저 아이디 조회
                                select(userJpaEntity.id)
                                        .from(userJpaEntity)
                                        .join(profileJpaEntity)
                                        .on(userJpaEntity.eq(profileJpaEntity.user))
                                        .where(profileJpaEntity.username.eq(username))
                        ))
        ));
    }

    static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException | NullPointerException e) {
            return new BooleanBuilder();
        }
    }
}
