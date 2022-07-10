package com.deukyun.realworld.article.adapter.in.web;


import static com.google.common.base.Preconditions.checkArgument;

public class PagingQueryParam {

    final long offset;
    final long limit;

    public PagingQueryParam() {
        this(0, 20);
    }

    public PagingQueryParam(long offset, long limit) {
        checkArgument(offset >= 0, "Offset must be greater or equals to zero.");
        checkArgument(limit >= 1, "Limit must be greater than zero.");

        this.offset = offset;
        this.limit = limit;
    }
}
