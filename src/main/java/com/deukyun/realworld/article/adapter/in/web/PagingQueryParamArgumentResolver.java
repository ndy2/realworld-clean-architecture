package com.deukyun.realworld.article.adapter.in.web;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static org.apache.commons.lang3.math.NumberUtils.toLong;

public class PagingQueryParamArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return PagingQueryParam.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        long offset = toLong(webRequest.getParameter("offset"), 0);
        long limit = toLong(webRequest.getParameter("limit"), 20);

        return new PagingQueryParam(offset, limit);
    }
}
