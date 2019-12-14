package com.kunlong.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

public class KunlongSql {
    public static SqlSessionBuilder sqlSessionBuilder = new SqlSessionBuilder();

    public static List<Map<String, Object>> selectList(StringBuilder sql) {
        return sqlSessionBuilder.selectTable(sql);
    }

    public static <T> List<T> selectList(SqlSession session, StringBuilder sql, Class<T> cls) {
        return sqlSessionBuilder.selectTable(session,sql, cls);
    }

    public static <T> List<T> selectList(StringBuilder sql, Class<T> cls) {
        return sqlSessionBuilder.selectTable(sql, cls);
    }

    public static <T> List<T> selectList(StringBuilder sql, Object value, Class<T> cls) {
        return sqlSessionBuilder.selectTable(sql, value, cls);
    }

    public static <T> T selectOne(StringBuilder sql, Object value, Class<T> cls) {
        return sqlSessionBuilder.selectOne(sql, value, cls);
    }

    public static <T> T selectOne(StringBuilder sql, Class<T> cls) {
        return sqlSessionBuilder.selectOne(sql, cls);
    }

    public static int delete(StringBuilder sql) {
        return sqlSessionBuilder.delete(sql);

    }

    public static int delete(StringBuilder sql, Object value) {
        return sqlSessionBuilder.delete(sql, value);

    }

    public static int insert(SqlSession session,StringBuilder sql, Object value) {
        return sqlSessionBuilder.insert(session,sql, value);

    }
    public static int insert(StringBuilder sql, Object value) {
        return sqlSessionBuilder.insert(sql, value);

    }

    public static int update(StringBuilder sql, Object value) {
        return sqlSessionBuilder.update(sql, value);

    }

    public static int update(StringBuilder sql) {
        return sqlSessionBuilder.update(sql);

    }

    public static int selectAutoID(SqlSession session) {
        return sqlSessionBuilder.selectAutoID(session);
    }
    //KunlongSql 提供了两个函数：分别是调存贮，函数返回
    //fnDb   spDb

    public static String fnDb(String fnName, Object... p) {

        return sqlSessionBuilder.fnDb(fnName, p);

    }

    public static List<Map<String, Object>> spDb(String fnName, Object... p) {
        int j = 0;
        for (Object i : p) {

            if (i instanceof String &&!p[j].toString().contains("'")) {
                p[j] = StringUtils.quote(p[j].toString());
            }
            j++;
        }
        return sqlSessionBuilder.spDb(null, fnName, p);
    }

    public static <T> List<T> spDb (Class<T> resultType,String fnName,  Object... p) {
        int j = 0;
        for (Object i : p) {

            if (i instanceof String) {
                p[j] = StringUtils.quote(p[j].toString());
            }
            j++;
        }
        return ( List<T> )sqlSessionBuilder.spDb(resultType, fnName,  p);

    }



}
