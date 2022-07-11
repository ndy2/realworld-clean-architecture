package com.deukyun.realworld.article.adapter.out.persistence;

import com.deukyun.realworld.profile.adapter.out.persistence.ProfileJpaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
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

    @ManyToOne(fetch = LAZY)
    private ProfileJpaEntity authorProfile;

    @OneToMany(cascade = PERSIST, mappedBy = "article")
    private List<ArticleTagJpaEntity> articleTags = new ArrayList<>();

    public ArticleJpaEntity(String slug, String title, String body, String description, long authorProfileId) {
        this.slug = slug;
        this.title = title;
        this.body = body;
        this.description = description;
        this.createdAt = now();
        this.authorProfile = ProfileJpaEntity.withId(authorProfileId);
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

    public Long getAuthorProfileId() {
        return authorProfile.getId();
    }

    public String getAuthorUsername() {
        return authorProfile.getUsername();
    }

    public String getAuthorBio() {
        return authorProfile.getBio();
    }

    public String getAuthorImage() {
        return authorProfile.getImage();
    }

}
