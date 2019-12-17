package com.kunlong.platform.service.impl;


import com.kunlong.platform.dao.LoginSsoMapper;
import com.kunlong.platform.model.LoginSso;
import com.kunlong.platform.model.LoginSsoExample;
import com.kunlong.platform.service.LoginSsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginSsoServiceImpl implements LoginSsoService {

    @Autowired
    LoginSsoMapper loginSsoMapper;

    @Override
    public int addLoginSso(LoginSso loginSso) {

       return loginSsoMapper.insertSelective(loginSso);


    }

    @Override
    public List<LoginSso> selectByExample(LoginSsoExample example) {

         return loginSsoMapper.selectByExample(example);


    }

    @Override
    public int deleteByExample(LoginSsoExample example) {

         return loginSsoMapper.deleteByExample(example);


    }


    @Override
    public int updateByPrimaryKeySelective(LoginSso record) {
           return loginSsoMapper.updateByPrimaryKeySelective(record);


    }

    @Override
    public int insertSelective(LoginSso record) {
         return loginSsoMapper.insertSelective(record);

    }


}
