package com.kunlong.platform.model;

import com.alibaba.fastjson.JSONObject;
import com.kunlong.platform.model.KunlongModel;

import java.sql.Date;

public final class Api_KeyModel extends KunlongModel {

    int userId;
    String apiKey = System.currentTimeMillis()+"";
    String securityKey = System.currentTimeMillis()+"";

    Date genTime;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public Date getGenTime() {
        return genTime;
    }

    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
