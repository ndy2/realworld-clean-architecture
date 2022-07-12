package com.deukyun.realworld.article.adapter.out.persistence.repository;

import com.deukyun.realworld.article.adapter.out.persistence.ArticleJpaEntity;
import com.deukyun.realworld.common.P6spyLogMessageFormatConfiguration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Sql("classpath:sql/ArticleSearchCondTest.sql")
@Import(P6spyLogMessageFormatConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleRepositoryCustomImplTest {

    /**
     * 데이터 세팅은 스크립트를 확인해주세용
     */
    @Autowired
    ArticleRepositoryCustomImpl repository;

    @Test
    void 조건없이() {
        List<ArticleJpaEntity> articles = repository.searchArticle(new ArticleSearchCond(null, null, null), 0, 20);

        assertArticle(articles.get(0), 7, 1, List.of("tag16"));
        assertArticle(articles.get(1), 8, 2, List.of("tag16", "tag17"));
        assertArticle(articles.get(2), 9, 3, List.of("tag16", "tag17", "tag18"));
    }

    @Test
    void favorited_조건_테스트() {
        List<ArticleJpaEntity> articles = repository.searchArticle(new ArticleSearchCond(null, null, "user2"), 0, 20);

        assertThat(articles).hasSize(2);
        assertArticle(articles.get(0), 8, 2, List.of("tag16", "tag17"));
        assertArticle(articles.get(1), 9, 3, List.of("tag16", "tag17", "tag18"));
    }

    /**
     * "tag16" -> article 7, 8, 9
     * "tag17" -> article 8, 9
     * "tag18" -> article 9
     */
    @Test
    void tag_조건_테스트() {
        List<ArticleJpaEntity> articles = repository.searchArticle(new ArticleSearchCond("tag16", null, null), 0, 20);

        assertArticle(articles.get(0), 7, 1, List.of("tag16"));
        assertArticle(articles.get(1), 8, 2, List.of("tag16", "tag17"));
        assertArticle(articles.get(2), 9, 3, List.of("tag16", "tag17", "tag18"));
    }

    @Test
    void author_이름_조건_테스트() {
        List<ArticleJpaEntity> articles = repository.searchArticle(new ArticleSearchCond(null, "user1", null), 0, 20);

        assertArticle(articles.get(0), 7, 1, List.of("tag16"));
    }

    @Nested
    class 다중_조건_테스트 {

        @Test
        void 태그와_favorite() {
            //태그 17 이면서  (8,9)
            //사용자 1의 favorite (7,8,9)
            List<ArticleJpaEntity> articles = repository.searchArticle(new ArticleSearchCond("tag17", null, "user1"), 0, 20);

            assertArticle(articles.get(0), 8, 2, List.of("tag16", "tag17"));
            assertArticle(articles.get(1), 9, 3, List.of("tag16", "tag17", "tag18"));
        }
    }

    // user3 -> 팔로우 user2,user1 -> 아티클 7,8
    @Test
    void 피드중인_사용자의_아티클_조회() {
        List<ArticleJpaEntity> articles = repository.feedArticles(3, 0, 20);

        assertArticle(articles.get(0), 7, 1, List.of("tag16"));
        assertArticle(articles.get(1), 8, 2, List.of("tag16", "tag17"));
    }

    private void assertArticle(ArticleJpaEntity article, int id, int userId, List<String> tags) {
        assertThat(article)
                .extracting(
                        ArticleJpaEntity::getId,
                        ArticleJpaEntity::getSlug,
                        ArticleJpaEntity::getTitle,
                        ArticleJpaEntity::getBody,
                        ArticleJpaEntity::getAuthorUsername,
                        ArticleJpaEntity::getAuthorBio,
                        ArticleJpaEntity::getAuthorImage
                ).containsExactly(
                        (long) id,
                        "article" + id + "slug",
                        "article" + id + "title",
                        "article" + id + "body",
                        "user" + userId,
                        "user" + userId + "bio",
                        "user" + userId + "image"
                );
        assertThat(article.getTagList())
                .containsExactlyElementsOf(tags);
    }
}