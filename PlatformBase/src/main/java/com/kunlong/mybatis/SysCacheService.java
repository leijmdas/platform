package com.kunlong.mybatis;

import java.util.List;
import java.util.Map;

public class SysCacheService extends KunlongSql {
    public static List<Map<String, Object>> select2Cache(String cacheKey, StringBuilder sql ) {

//        List<Map<String, Object>> list = getCache().get(cacheKey, List.class);
//        if (list == null) {
//            if(check){
//                checkCached(cacheKey);
//            }
//            YtbLog.logDebug(sql);
//            if (sql.toString().indexOf("limit ") < 0) {
//                sql.append(" limit ").append(MAX_CACHE_NUMBER);
//            }
//            list = YtbSql.selectList(sql);
//            getCache().put(cacheKey, list);
//            if (list.size() > MAX_CACHE_NUMBER - 1) {
//                throw new YtbError(YtbError.CODE_CACHE_TOO_MANNY);
//            }
//        }
        return selectList(sql);
    }
}
