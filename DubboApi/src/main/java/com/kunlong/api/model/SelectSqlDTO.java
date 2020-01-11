package com.kunlong.api.model;

import org.mockito.internal.util.StringUtil;

//import org.apache.poi.util.StringUtil;

/**
 * Created by ZYB on 2018/9/13 14:43
 */
public class SelectSqlDTO {

    private Integer metadataId ;

    private String db;

    private String table;

    private String where;

    private String orderBy;

    private Integer start = 0;
    private Integer limit = 20;
    private Integer pageNo = null;


    public Integer getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(Integer metadataId) {
        this.metadataId = metadataId;
    }

    public StringBuilder buildCountSql() {

        this.setLimit(-1);
        StringBuilder sb = new StringBuilder(246);
        sb.append(" select count(*) from ") ;

        return buildBaseSql(sb);
    }
    public StringBuilder buildSql() {
        StringBuilder sb = new StringBuilder(246);
        sb.append(" select * from ") ;
        return buildBaseSql(sb);
    }
    public StringBuilder buildBaseSql(StringBuilder sb) {


        if (db != null && !db.isEmpty()) {
            sb.append(db).append(".");
        }
        sb.append(table);

        if (where != null && !where.isEmpty()) {
            sb.append(" where ").append(where);
        }
        if (orderBy != null && !orderBy.isEmpty()) {
            sb.append(" order by  ").append(orderBy);
        }
        if(limit==-1){
            sb.append(" limit  0,10000");
        }else {
            sb.append(" limit  ").append(start).append(",").append(limit);
        }
        return sb;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }



}
