package com.kunlong.platform.context.RestMessage;

import com.alibaba.fastjson.JSONObject;
import com.kunlong.platform.model.KunlongModel;

import java.util.ArrayList;
import java.util.List;

public class MsgBody extends KunlongModel {
    List<Object> list = new ArrayList<>();

    public JSONObject toJSONObject(){
        return JSONObject.parseObject(this.toString());
    }

    public void addObject(Object o) {
        this.list.add(o);
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }


}
