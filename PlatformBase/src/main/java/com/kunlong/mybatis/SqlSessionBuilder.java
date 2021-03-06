package com.kunlong.mybatis;

import com.github.abel533.sql.SqlMapper;
import com.kunlong.platform.model.KunlongError;
import org.apache.ibatis.session.SqlSession;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class SqlSessionBuilder implements ISqlSessionBuilder {
    static Map<String, String> map_db_config = new LinkedHashMap<>();

    static {
        map_db_config.put(DB_NAME_COMMON, "mapper/manager/MyBatisConfig.xml");
        map_db_config.put(DB_NAME_MANAGER, "mapper/manager/MyBatisConfig.xml");
        map_db_config.put(DB_NAME_USER, "mapper/user/MyBatisConfig.xml");
        map_db_config.put(DB_NAME_PROJECT, "mapper/project/MyBatisConfig.xml");
        map_db_config.put(DB_NAME_BANGBANG, "mapper/bangbang/MyBatisConfig.xml");
        map_db_config.put(DB_NAME_ACCOUNT, "mapper/account/MyBatisConfig.xml");
        map_db_config.put(DB_NAME_TASKLOG, "mapper/log/MyBatisConfig.xml");
        //map_db_config.put(DB_NAME_ACTIVITI, "mapper/activiti/MyBatisConfig.xml");
    }

    private ReentrantLock lock = new ReentrantLock();
    public SqlSession getSession() {
        return getSession(DB_NAME_COMMON, true);
    }

    public SqlSession getSessionCommon(boolean isAutoCommit) {
        return getSession(DB_NAME_COMMON, isAutoCommit);
    }

    public SqlSession getSession_manager(boolean isAutoCommit) {
       // DefaultSqlSessionFactory sessionFactory = AppCtxtUtil.getBean
        //        ("commonSqlSessionFactory", DefaultSqlSessionFactory.class);
       // return  sessionFactory.openSession(isAutoCommit);
        return getSession(DB_NAME_MANAGER, isAutoCommit);
    }

    public SqlSession getSession_user(boolean isAutoCommit) {
        return getSession(DB_NAME_USER, isAutoCommit);
    }

    public SqlSession getSession_project(boolean isAutoCommit) {
        return getSession(DB_NAME_PROJECT, isAutoCommit);
    }

    public SqlSession getSession_bangbang(boolean isAutoCommit) {
        return getSession(DB_NAME_BANGBANG, isAutoCommit);
    }

    public SqlSession getSession_account(boolean isAutoCommit) {
        return getSession(DB_NAME_ACCOUNT, isAutoCommit);
    }

    public SqlSession getSession_tasklog(boolean isAutoCommit) {
        return getSession(DB_NAME_TASKLOG, isAutoCommit);
    }

    public SqlSession getSession_activiti(boolean isAutoCommit) {

        return getSession(DB_NAME_ACTIVITI, isAutoCommit);
    }

    public SqlSession getSession(String dbname, boolean isAutoCommit) {
        try {
            return getSqlSessionFactroy(dbname).getSession(isAutoCommit);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new KunlongError(KunlongError.CODE_DEFINE_ERROR, "getSession error:" + ex.getMessage());

        }
    }

    SqlSessionFactory getSqlSessionFactroy(String dbname) {
        if (map_factroy.get(dbname) == null) {
            String cfg_name = map_db_config.get(dbname);
            try {
                lock.lock();
                map_factroy.put(dbname, new SqlSessionFactory(cfg_name));
            } finally {
                lock.unlock();
            }
        }
        return map_factroy.get(dbname);
    }

    public String fnDb(String fnName, Object... p) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("select ").append(fnName);
        sql.append("(");
        sql.append(Arrays.stream(p).map(x -> x.toString()).collect(Collectors.joining(",")));
        sql.append(")");
        //YtbLog.logDebug(sql);
        return selectOne(sql, String.class);

    }

    public <T> List<T> selectList(String sql, Class<T> resultType) {

        SqlSession session = getSession();
        try {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql.toString(),resultType);
        } finally {
            if(session!=null) session.close();
        }
    }

    public <T> List<T> spDb(Class<T> resultType, String fnName, Object... p) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("call ").append(fnName);
        sql.append("(");
        sql.append(Arrays.stream(p).map(x -> x.toString()).collect(Collectors.joining(",")));
        sql.append(")");

        //YtbLog.logDebug("Enter spDb "+fnName, sql);
        if (resultType == null) {
            return (List<T>) selectTable(sql);
        }
        try {
            List<T> lst = selectList(sql.toString(), resultType);
            //YtbLog.logDebug("spDb result", lst.toString());
            return lst;
        } finally {
            //YtbLog.logDebug("Exit spDb "+fnName, sql);
        }

    }

    public int selectAutoID(SqlSession session){
        SqlMapper m = new SqlMapper(  session );
        int key = m.selectOne("select LAST_INSERT_ID() as id ;",Integer.class);
        return key;
    }


    public int updateSql(StringBuilder sql) {
        try(SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            m.update(sql.toString());
            return selectAutoID(session);
        }
    }

    public List<Map<String, Object>> selectTable(StringBuilder sql) {

        try(SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql.toString());
        }
    }

    public <T> List<T> selectTable( SqlSession session,StringBuilder sql, Class<T> cls) {
        return selectTable(session,sql.toString(),cls);
    }

    public <T> List<T> selectTable(StringBuilder sql, Class<T> cls) {
        return selectTable(sql.toString(),cls);
    }
    public <T> List<T> selectTable(SqlSession session,String  sql, Class<T> cls) {

            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql ,cls);

    }
    public <T> List<T> selectTable(String  sql, Class<T> cls) {

        try(SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql ,cls);
        }
    }
    public <T> List<T> selectTable(StringBuilder  sql,Object value, Class<T> cls) {
        try(SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.selectList(sql.toString(),value ,cls);
        }
    }

    public <T> T selectOne(StringBuilder sql, Class<T> cls) {
        return selectOne(sql.toString(),cls);
    }

    public <T> T selectOne(String sql, Class<T> cls) {
        try(SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            return m.selectOne(sql, cls);
        }
    }

    public <T> T selectOne(StringBuilder sql, Object value, Class<T> cls) {
        return selectOne(sql.toString(), value, cls);
    }

    public <T> T selectOne(String sql, Object value, Class<T> cls) {
        try(SqlSession session = getSession()) {

            SqlMapper m = new SqlMapper(session);
            return m.selectOne(sql.toString(), value, cls);
        }
    }

    public int delete(StringBuilder sql) {
        try(SqlSession session = getSession()) {

            SqlMapper m = new SqlMapper(session);
            m.delete(sql.toString());
            return 0;
        }
    }

    public int delete(StringBuilder sql, Object value) {

        try ( SqlSession session = getSession()){
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

    public int insert(StringBuilder sql,Object value) {

        try(SqlSession session = getSession()) {
            SqlMapper m = new SqlMapper(session);
            m.insert(sql.toString(),value);
            return selectAutoID(session);
        }
    }
    public int update(StringBuilder sql, Object value) {

        try (SqlSession session = getSession()){
            SqlMapper m = new SqlMapper(session);
            return m.update(sql.toString(), value);
            //return selectAutoID(session);
        }
    }

    public int update(StringBuilder sql) {

        try(SqlSession session = getSession()) {
            SqlMapper sqlMapper = new SqlMapper(session);
            return sqlMapper.update(sql.toString());
        }
    }

//<tx:annotation-driven/>
//
//<bean id="transactionManager1" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
//    <property name="dataSource" ref="datasource1"></property>
//    <qualifier value="datasource1Tx"/>
//</bean>
//
//<bean id="transactionManager2" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
//    <property name="dataSource" ref="datasource2"></property>
//    <qualifier value="datasource2Tx"/>
//</bean>
//    复制代码
//    使用时，用@Transactional("datasource1Tx")和@Transactional("datasource2Tx")，来区别具体使用某个事务管理器
//
//            复制代码
//    public class TransactionalService {
//
//        @Transactional("datasource1Tx")
//        public void setSomethingInDatasource1() { ... }
//
//        @Transactional("datasource2Tx")
//        public void doSomethingInDatasource2() { ... }
//    }
}
