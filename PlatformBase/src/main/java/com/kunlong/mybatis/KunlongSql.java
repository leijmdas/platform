package com.kunlong.mybatis;

import com.kunlong.platform.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;


public class KunlongSql {
    public static SqlSessionUtil sqlSessionUtil = new SqlSessionUtil();

    public static List<Map<String, Object>> selectList(StringBuilder sql) {
        return sqlSessionUtil.selectTable(sql);
    }

    public static <T> List<T> selectList(SqlSession session, StringBuilder sql, Class<T> cls) {
        return sqlSessionUtil.selectTable(session,sql, cls);
    }

    public static <T> List<T> selectList(StringBuilder sql, Class<T> cls) {
        return sqlSessionUtil.selectTable(sql, cls);
    }

    public static <T> List<T> selectList(StringBuilder sql, Object value, Class<T> cls) {
        return sqlSessionUtil.selectTable(sql, value, cls);
    }

    public static <T> T selectOne(StringBuilder sql, Object value, Class<T> cls) {
        return sqlSessionUtil.selectOne(sql, value, cls);
    }

    public static <T> T selectOne(StringBuilder sql, Class<T> cls) {
        return sqlSessionUtil.selectOne(sql, cls);
    }

    public static int delete(StringBuilder sql) {
        return sqlSessionUtil.delete(sql);

    }

    public static int delete(StringBuilder sql, Object value) {
        return sqlSessionUtil.delete(sql, value);

    }

    public static int insert(SqlSession session,StringBuilder sql, Object value) {
        return sqlSessionUtil.insert(session,sql, value);

    }
    public static int insert(StringBuilder sql, Object value) {
        return sqlSessionUtil.insert(sql, value);

    }

    public static int update(StringBuilder sql, Object value) {
        return sqlSessionUtil.update(sql, value);

    }

    public static int update(StringBuilder sql) {
        return sqlSessionUtil.update(sql);

    }

    public static int selectAutoID(SqlSession session) {
        return sqlSessionUtil.selectAutoID(session);
    }
    //KunlongSql 提供了两个函数：分别是调存贮，函数返回
    //fnDb   spDb

    public static String fnDb(String fnName, Object... p) {

        return sqlSessionUtil.fnDb(fnName, p);

    }

    public static List<Map<String, Object>> spDb(String fnName, Object... p) {
        int j = 0;
        for (Object i : p) {

            if (i instanceof String &&!p[j].toString().contains("'")) {
                p[j] = StringUtils.quote(p[j].toString());
            }
            j++;
        }
        return sqlSessionUtil.spDb(null, fnName, p);
    }

    public static <T> List<T> spDb (Class<T> resultType,String fnName,  Object... p) {
        int j = 0;
        for (Object i : p) {

            if (i instanceof String) {
                p[j] = StringUtils.quote(p[j].toString());
            }
            j++;
        }
        return ( List<T> ) sqlSessionUtil.spDb(resultType, fnName,  p);

    }



}
