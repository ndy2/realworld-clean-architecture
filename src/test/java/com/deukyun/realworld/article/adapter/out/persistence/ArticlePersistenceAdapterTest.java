package com.deukyun.realworld.article.adapter.out.persistence;

import com.deukyun.realworld.article.application.port.out.dto.command.InsertArticleCommand;
import com.deukyun.realworld.article.application.port.out.dto.command.InsertArticleResult;
import com.deukyun.realworld.article.application.port.out.dto.query.FindArticleResult;
import com.deukyun.realworld.article.application.port.out.dto.query.FindAuthorResult;
import com.deukyun.realworld.common.P6spyLogMessageFormatConfiguration;
import com.deukyun.realworld.profile.adapter.out.persistence.ProfileJpaEntity;
import com.deukyun.realworld.user.adapter.out.persistence.UserJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static com.deukyun.realworld.article.adapter.out.persistence.ArticleJpaEntity.ArticleTagJpaEntity;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 쿼리를 확인하자
 */
@Import({ArticlePersistenceAdapter.class, P6spyLogMessageFormatConfiguration.class})
@DataJpaTest
class ArticlePersistenceAdapterTest {

    @Autowired
    ArticlePersistenceAdapter adapter;

    @Autowired
    EntityManager em;

    ProfileJpaEntity profile;

    @BeforeEach
    void setUp() {
        UserJpaEntity user = new UserJpaEntity("jake@jake.jake", "jakejake");
        em.persist(user);
        profile = new ProfileJpaEntity("Jakob", user.getId());
        profile.update(null, "I love skateboard", "haha.png");
        em.persist(profile);

        em.flush();
        em.clear();
    }

    /**
     * Hibernate:
     * insert into article (author_profile_id, body, created_at, description, slug, title, updated_at, id)
     * values (1, You have to believe , 2022-07-10T18:00:03.457141, Ever wonder how?, how-to-train-your-dragon, How to train your dragon, null, 1)
     */
    @Test
    void 아티클_저장_태그_없이() {
        //given
        InsertArticleCommand insertArticleCommand = new InsertArticleCommand(
                "how-to-train-your-dragon",
                "How to train your dragon",
                "Ever wonder how?",
                "You have to believe",
                Collections.emptyList(),
                profile.getId()
        );

        //when
        InsertArticleResult result = adapter.insertArticle(insertArticleCommand);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getCreatedAt()).isNotNull();

        em.flush();
    }

    /**
     * 삽입 쿼리 로그
     * <p>
     * Hibernate: insert into tag (name, id) values (reactjs, 1)
     * Hibernate: insert into tag (name, id) values (angularjs, 2)
     * Hibernate: insert into tag (name, id) values (dragons, 3)
     *
     * <p>
     * Hibernate:
     * insert into article (author_profile_id, body, created_at, description, slug, title, updated_at, id)
     * values (1, You have to believe , 2022-07-10T18:00:03.457141, Ever wonder how?, how-to-train-your-dragon, How to train your dragon, null, 4)
     *
     * <p>
     * Hibernate: insert into article_tag (article_id, tag_id, id) values (4, 1, 5)
     * Hibernate: insert into article_tag (article_id, tag_id, id) values (4, 2, 6)
     * Hibernate: insert into article_tag (article_id, tag_id, id) values (4, 3, 7)
     */
    @Test
    void 아티클_저장_태그_포함() {
        //given
        InsertArticleCommand insertArticleCommand = new InsertArticleCommand(
                "how-to-train-your-dragon",
                "How to train your dragon",
                "Ever wonder how?",
                "You have to believe",
                List.of("reactjs", "angularjs", "dragons"),
                profile.getId()
        );

        //when
        InsertArticleResult result = adapter.insertArticle(insertArticleCommand);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getCreatedAt()).isNotNull();
    }

    /**
     * 같은 게시글 두번 저장 하고 쿼리 로그 확인 - 태그 삽입 쿼리가 총 4번 나가야함
     * <p>
     * <p>
     * 두번째 아티클 삽입에 대한 삽입 쿼리 로그 ("====flushed=====" 출력 이후)
     * Hibernate: insert into tag (name, id) values (new-tag, 8)
     * <p>
     * Hibernate:
     * insert into article (author_profile_id, body, created_at, description, slug, title, updated_at, id)
     * values (1, You have to believe , 2022-07-10T18:00:03.457141, Ever wonder how?, how-to-train-your-dragon, How to train your dragon, null, 9)
     *
     * <p>
     * Hibernate: insert into article_tag (article_id, tag_id, id) values (9, 1, 10)
     * Hibernate: insert into article_tag (article_id, tag_id, id) values (9, 2, 11)
     * Hibernate: insert into article_tag (article_id, tag_id, id) values (9, 3, 12)
     * Hibernate: insert into article_tag (article_id, tag_id, id) values (9, 8, 13)
     */


    @Test
    void 아티클_저장_태그_중복() {
        //given
        InsertArticleCommand insertArticleCommand1 = new InsertArticleCommand(
                "how-to-train-your-dragon",
                "How to train your dragon",
                "Ever wonder how?",
                "You have to believe",
                List.of("reactjs", "angularjs", "dragons"),
                profile.getId()
        );
        InsertArticleCommand insertArticleCommand2 = new InsertArticleCommand(
                "how-to-train-your-dragon",
                "How to train your dragon",
                "Ever wonder how?",
                "You have to believe",
                List.of("reactjs", "angularjs", "dragons", "new-tag"),
                profile.getId()
        );

        //when
        InsertArticleResult result1 = adapter.insertArticle(insertArticleCommand1);
        em.flush();
        em.clear();
        System.out.println("====flushed=====");
        InsertArticleResult result2 = adapter.insertArticle(insertArticleCommand2);

        //then
        assertThat(result1).isNotNull();
        assertThat(result1.getCreatedAt()).isNotNull();
        assertThat(result2).isNotNull();
        assertThat(result2.getCreatedAt()).isNotNull();
    }


    @Test
    void 아티클_slug_로_조회() {
        //setup
        ArticleJpaEntity article = new ArticleJpaEntity(
                "how-to-train-your-dragon",
                "How to train your dragon",
                "Ever wonder how?",
                "You have to believe",
                profile.getId());

        addTag(article, "reactjs");
        addTag(article, "angularjs");
        addTag(article, "dragons");

        em.persist(article);
        em.flush();
        em.clear();

        //given
        String slug = "how-to-train-your-dragon";

        //when
        FindArticleResult articleResult = adapter.findArticleBySlug(slug);

        //then
        assertThat(articleResult).isNotNull();

        // article 검증
        assertThat(articleResult.getUpdatedAt()).isNull();
        assertThat(articleResult).extracting(FindArticleResult::getId, FindArticleResult::getCreatedAt).doesNotContainNull();
        assertThat(articleResult)
                .extracting(
                        FindArticleResult::getSlug,
                        FindArticleResult::getTitle,
                        FindArticleResult::getBody,
                        FindArticleResult::getDescription
                ).containsExactly(
                        "how-to-train-your-dragon",
                        "How to train your dragon",
                        "Ever wonder how?",
                        "You have to believe"
                );

        // author profile fetch 검증
        assertThat(articleResult.getAuthor())
                .extracting(
                        FindAuthorResult::getId,
                        FindAuthorResult::getUsername,
                        FindAuthorResult::getBio,
                        FindAuthorResult::getImage
                ).containsExactly(
                        profile.getId(),
                        "Jakob",
                        "I love skateboard",
                        "haha.png"
                );

        // tag fetch 검증
        assertThat(articleResult.getTagList())
                .containsExactly("reactjs", "angularjs", "dragons");
    }

    private void addTag(ArticleJpaEntity article, String name) {
        TagJpaEntity tag = new TagJpaEntity(name);
        em.persist(tag);
        article.addArticleTag(new ArticleTagJpaEntity(tag));
    }

}