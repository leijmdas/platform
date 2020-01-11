package com.kunlong.platform.utils;

import com.github.abel533.sql.SqlMapper;
import com.kunlong.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.api.model.SelectSqlDTO;
import com.kunlong.platform.context.AppKlongContext;
import com.kunlong.platform.model.KunlongError;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SqlSessionUtil {


    SqlSessionFactory getSqlSessionFactroy() {

        return AppKlongContext.getSqlSessionFactory();
    }

    public SqlSession getSession() {
        return getSession(true);
    }

    public SqlSession getSession(boolean isAutoCommit) {
        try {
            return getSqlSessionFactroy().openSession(isAutoCommit);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new KunlongError(KunlongError.CODE_DEFINE_ERROR, "getSession error:" + ex.getMessage());

        }
    }


    public String fnDb(String fnName, Object... p) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("select ").append(fnName);
        sql.append("(");
        sql.append(Arrays.stream(p).map(x -> x.toString()).collect(Collectors.joining(",")));
        sql.append(")");
        return selectOne(sql, String.class);

    }

    public  List<Map<String, Object>>  selectList(String sql ) {

        SqlSession session = getSession();
        try {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql.toString());
        } finally {
            if (session != null) session.close();
        }
    }

    public <T> List<T> selectList(String sql, Class<T> resultType) {

        SqlSession session = getSession();
        try {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql.toString(), resultType);
        } finally {
            if (session != null) session.close();
        }
    }

    public <T> List<T> spDb(Class<T> resultType, String fnName, Object... p) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("call ").append(fnName);
        sql.append("(");
        sql.append(Arrays.stream(p).map(x -> x.toString()).collect(Collectors.joining(",")));
        sql.append(")");

        if (resultType == null) {
            return (List<T>) selectTable(sql);
        }
        try {
            List<T> lst = selectList(sql.toString(), resultType);
            return lst;
        } finally {

        }

    }

    public int selectAutoID(SqlSession session) {
        SqlMapper m = new SqlMapper(session);
        int key = m.selectOne("select LAST_INSERT_ID() as id ;", Integer.class);
        return key;
    }


    public int updateSql(StringBuilder sql) {
        try (SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            m.update(sql.toString());
            return selectAutoID(session);
        }
    }
    public long countTable(MetadataQueryDTO qp) {
        qp.getParam().setStart(qp.getStart());
        qp.getParam().setLimit(qp.getLimit());


        try (SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.selectOne(qp.getParam().buildCountSql().toString(),Long.class);
        }
    }

    public List<Map<String, Object>> selectTable(MetadataQueryDTO qp) {
        qp.getParam().setStart(qp.getStart());
        qp.getParam().setLimit(qp.getLimit());

        try (SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(qp.getParam().buildSql().toString());
        }
    }

    public List<Map<String, Object>> selectTable(StringBuilder sql) {

        try (SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql.toString());
        }
    }

    public <T> List<T> selectTable(SqlSession session, StringBuilder sql, Class<T> cls) {
        return selectTable(session, sql.toString(), cls);
    }

    public <T> List<T> selectTable(StringBuilder sql, Class<T> cls) {
        return selectTable(sql.toString(), cls);
    }

    public <T> List<T> selectTable(SqlSession session, String sql, Class<T> cls) {

        SqlMapper m = new SqlMapper(session);
        return m.selectList(sql, cls);

    }

    public <T> List<T> selectTable(String sql, Class<T> cls) {

        try (SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql, cls);
        }
    }

    public <T> List<T> selectTable(StringBuilder sql, Object value, Class<T> cls) {
        try (SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql.toString(), value, cls);
        }
    }

    public <T> T selectOne(StringBuilder sql, Class<T> cls) {
        return selectOne(sql.toString(), cls);
    }

    public <T> T selectOne(String sql, Class<T> cls) {
        try (SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.selectOne(sql, cls);
        }
    }

    public <T> T selectOne(StringBuilder sql, Object value, Class<T> cls) {
        return selectOne(sql.toString(), value, cls);
    }

    public <T> T selectOne(String sql, Object value, Class<T> cls) {
        try (SqlSession session = getSession()) {

            SqlMapper m = new SqlMapper(session);
            return m.selectOne(sql.toString(), value, cls);
        }
    }

    public int delete(StringBuilder sql) {
        try (SqlSession session = getSession()) {

            SqlMapper m = new SqlMapper(session);
            m.delete(sql.toString());
            return 0;
        }
    }

    public int delete(StringBuilder sql, Object value) {

        try (SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            m.delete(sql.toString(), value);
            return 0;
        }
    }

    public int insert(SqlSession session, StringBuilder sql, Object value) {

        SqlMapper m = new SqlMapper(session);
        m.insert(sql.toString(), value);
        return selectAutoID(session);

    }

    public int insert(StringBuilder sql, Object value) {

        try (SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            m.insert(sql.toString(), value);
            return selectAutoID(session);
        }
    }

    public int update(StringBuilder sql, Object value) {

        try (SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.update(sql.toString(), value);
        }
    }

    public int update(StringBuilder sql) {

        try (SqlSession session = getSession()) {
            SqlMapper sqlMapper = new SqlMapper(session);
            return sqlMapper.update(sql.toString());
        }
    }

}
