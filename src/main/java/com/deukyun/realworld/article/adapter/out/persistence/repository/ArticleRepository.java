package com.deukyun.realworld.article.adapter.out.persistence.repository;

import com.deukyun.realworld.article.adapter.out.persistence.ArticleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleJpaEntity, Long>, ArticleRepositoryCustom {

}
