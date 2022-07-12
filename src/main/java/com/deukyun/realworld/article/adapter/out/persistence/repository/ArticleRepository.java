package com.deukyun.realworld.article.adapter.out.persistence.repository;

import com.deukyun.realworld.article.adapter.out.persistence.ArticleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleJpaEntity, Long>, ArticleRepositoryCustom {

    @Query("select a from ArticleJpaEntity a join a.authorProfile p join p.user u where u.id = :userId and a.slug = :slug")
    Optional<ArticleJpaEntity> findByUserIdAndSlug(long userId, String slug);
}
