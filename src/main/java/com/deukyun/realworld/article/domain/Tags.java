package com.deukyun.realworld.article.domain;

import lombok.Value;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Tags {

    private final List<Tag> values;

    public Tags(List<String> values) {

        this.values = values.stream().map(Tag::new).collect(toList());
    }

    public List<String> getTagList() {
        return values.stream().map(Tag::getValue).collect(toList());
    }

    @Value
    private static class Tag {
        String value;
    }
}
