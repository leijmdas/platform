package com.kunlong.dubbo.api.service;

import com.kunlong.dubbo.api.dto.queryParam.MetadataQueryDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public interface SqlSessionApiService {


    SqlSessionFactory getSqlSessionFactroy()  ;



    public SqlSession getSession();

    public SqlSession getSession(boolean isAutoCommit) ;


    public String fnDb(String fnName, Object... p);

    public List<Map<String, Object>> selectList(StringBuilder sql);
    public <T> List<T> selectList(StringBuilder sql, Class<T> resultType);

    public List<Map<String, Object>> selectList(String sql) ;;
    public <T> List<T> selectList(String sql, Class<T> resultType);

    public <T> List<T> spDb(Class<T> resultType, String fnName, Object... p) ;


    public int selectAutoID(SqlSession session);

    public int updateSql(StringBuilder sql)  ;

    public long countTable(MetadataQueryDTO qp) ;

    public List<Map<String, Object>> selectTable(MetadataQueryDTO qp) ;
    public List<Map<String, Object>> selectTable(StringBuilder sql);

    public <T> List<T> selectTable(SqlSession session, StringBuilder sql, Class<T> cls) ;

    public <T> List<T> selectTable(StringBuilder sql, Class<T> cls) ;
    public <T> List<T> selectTable(SqlSession session, String sql, Class<T> cls) ;

    public <T> List<T> selectTable(String sql, Class<T> cls) ;

    public <T> List<T> selectTable(StringBuilder sql, Object value, Class<T> cls) ;

    public <T> T selectOne(StringBuilder sql, Class<T> cls) ;
    public <T> T selectOne(String sql, Class<T> cls) ;


    public <T> T selectOne(StringBuilder sql, Object value, Class<T> cls) ;

    public <T> T selectOne(String sql, Object value, Class<T> cls) ;

    public int delete(StringBuilder sql) ;

    public int delete(StringBuilder sql, Object value);

    public int insert(SqlSession session, StringBuilder sql, Object value);
    public int insert(StringBuilder sql, Object value) ;

    public int update(StringBuilder sql, Object value);

    public int update(StringBuilder sql) ;

}
