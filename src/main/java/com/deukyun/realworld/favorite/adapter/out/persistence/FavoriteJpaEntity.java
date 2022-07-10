package com.deukyun.realworld.favorite.adapter.out.persistence;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static lombok.AccessLevel.PROTECTED;

@Table(name = "favorite")
@Entity
@NoArgsConstructor(access = PROTECTED)
class FavoriteJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private long userId;
    private long articleId;

    public Long getId() {
        return id;
    }

    public FavoriteJpaEntity(long userId, long articleId) {
        this.userId = userId;
        this.articleId = articleId;
    }
}
