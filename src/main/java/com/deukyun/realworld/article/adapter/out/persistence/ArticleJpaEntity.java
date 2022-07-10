package com.deukyun.realworld.article.adapter.out.persistence;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Table(name = "article")
@Entity
@NoArgsConstructor(access = PROTECTED)
class ArticleJpaEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String slug;
    private String title;
    private String body;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private long authorProfileId;

    @OneToMany(cascade = PERSIST, mappedBy = "article")
    private List<ArticleTagJpaEntity> articleTags = new ArrayList<>();

    public ArticleJpaEntity(String slug, String title, String body, String description, long authorProfileId) {
        this.slug = slug;
        this.title = title;
        this.body = body;
        this.description = description;
        this.createdAt = now();
        this.authorProfileId = authorProfileId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addArticleTag(ArticleTagJpaEntity articleTag) {
        //update reference
        articleTag.article = this;
        articleTags.add(articleTag);
    }

    @Table(name = "article_tag")
    @Entity
    @NoArgsConstructor(access = PROTECTED)
    static class ArticleTagJpaEntity {

        @Id
        @GeneratedValue
        private Long id;
        private long tagId;

        @ManyToOne(fetch = LAZY)
        @JoinColumn
        private ArticleJpaEntity article;

        public ArticleTagJpaEntity(long tagId) {
            this.tagId = tagId;
        }
    }
}