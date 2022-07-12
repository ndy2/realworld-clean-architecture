package com.deukyun.realworld.article.adapter.out.persistence;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Table(name = "tag")
@Entity
@NoArgsConstructor(access = PROTECTED)
public class TagJpaEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    public TagJpaEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEqual(TagJpaEntity tagJpaEntity) {
        return name.equals(tagJpaEntity.name);
    }
}
