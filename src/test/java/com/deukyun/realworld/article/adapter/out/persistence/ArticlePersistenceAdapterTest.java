package com.deukyun.realworld.article.adapter.out.persistence;

import com.deukyun.realworld.article.application.port.out.InsertArticleCommand;
import com.deukyun.realworld.article.application.port.out.InsertArticleResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 쿼리를 확인하자
 */
@Import(ArticlePersistenceAdapter.class)
@DataJpaTest
@TestPropertySource(properties = {
        "logging.level.org.hibernate.SQL=DEBUG",
        "logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE"
})
class ArticlePersistenceAdapterTest {

    @Autowired
    ArticlePersistenceAdapter adapter;

    @Autowired
    EntityManager em;

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
                1L
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
                1L
        );

        //when
        InsertArticleResult result = adapter.insertArticle(insertArticleCommand);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getCreatedAt()).isNotNull();

        em.flush();
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
                1L
        );
        InsertArticleCommand insertArticleCommand2 = new InsertArticleCommand(
                "how-to-train-your-dragon",
                "How to train your dragon",
                "Ever wonder how?",
                "You have to believe",
                List.of("reactjs", "angularjs", "dragons", "new-tag"),
                1L
        );

        //when
        InsertArticleResult result1 = adapter.insertArticle(insertArticleCommand1);
        em.flush();
        System.out.println("====flushed=====");
        InsertArticleResult result2 = adapter.insertArticle(insertArticleCommand2);

        //then
        assertThat(result1).isNotNull();
        assertThat(result1.getCreatedAt()).isNotNull();
        assertThat(result2).isNotNull();
        assertThat(result2.getCreatedAt()).isNotNull();

        em.flush();
    }


}