package com.kunlong.service;




import com.kunlong.model.LoginSso;
import com.kunlong.model.LoginSsoExample;

import java.util.List;

/**
 * Package: ytb.activiti.service
 * Author: ZCS
 * Date: Created in 2018/8/23 16:35
 */
public interface LoginSsoService {

    //获取通知列表

    //获取通知
    int addLoginSso(LoginSso loginSso);

    List<LoginSso> selectByExample(LoginSsoExample example);

    int updateByPrimaryKeySelective(LoginSso record);

    int deleteByExample(LoginSsoExample example);

    int insertSelective(LoginSso record);
}
