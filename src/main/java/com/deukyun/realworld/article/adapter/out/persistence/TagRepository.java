package com.deukyun.realworld.article.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagJpaEntity, Long> {

    List<TagJpaEntity> findByNameIn(List<String> names);
}
