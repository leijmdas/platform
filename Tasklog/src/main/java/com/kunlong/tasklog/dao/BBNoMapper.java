package com.kunlong.tasklog.dao;

import com.kunlong.tasklog.model.BBNoModel;

import java.util.List;

/**
 * Package: ytb.common.basic.userid.dao
 * Author: ZCS
 * Date: Created in 2018/10/16 18:58
 */
public interface BBNoMapper {

    //添加信息
    void addBBNOInfo(BBNoModel bbNoModel);

    //获取
    BBNoModel getBBNoByUUId(String UUId);
    List<BBNoModel > selectBBNo(   );


}
