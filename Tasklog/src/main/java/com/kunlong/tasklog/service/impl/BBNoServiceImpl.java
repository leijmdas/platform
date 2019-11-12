package com.kunlong.tasklog.service.impl;

import com.kunlong.tasklog.dao.BBNoMapper;
import com.kunlong.tasklog.model.BBNoModel;
import com.kunlong.tasklog.service.BBNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Package: ytb.common.basic.userid.service.impl
 * Author: ZCS
 * Date: Created in 2018/10/16 19:02
 */
@Service
public class BBNoServiceImpl implements BBNoService {
    @Autowired
    BBNoMapper bBNoMapper;
    @Override
    public void addBBNOInfo(BBNoModel bbNoModel) {
        bBNoMapper.addBBNOInfo(bbNoModel);
    }

    @Override
    public BBNoModel getBBNoByUUId(String UUId) {
        return bBNoMapper.getBBNoByUUId(UUId);


    }

    @Override
    public List<BBNoModel> selectBBNo () {
        return bBNoMapper.selectBBNo ( );

    }
}
