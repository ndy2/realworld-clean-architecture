package com.deukyun.realworld.common;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;

import java.util.Locale;

public class CustomP6spySqlFormat implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return formatSql(category, sql);
    }

    private String formatSql(String category, String sql) {
        // 여기 부터
        if (sql == null || sql.trim().equals("")) return sql;

        // Only format Statement, distinguish DDL And DML
        if (Category.STATEMENT.getName().equals(category)) {
            String tmpsql = sql.trim().toLowerCase(Locale.ROOT);
            if (tmpsql.startsWith("create") || tmpsql.startsWith("alter") || tmpsql.startsWith("comment")) {
                sql = FormatStyle.DDL.getFormatter().format(sql);
            } else {
                sql = FormatStyle.BASIC.getFormatter().format(sql);
            }
        }
        // 여기 까지 주석하면 한줄 출력
        return sql;
    }
}
