package com.kunlong.service.impl;


import com.kunlong.dao.LoginSsoMapper;
import com.kunlong.model.LoginSso;
import com.kunlong.model.LoginSsoExample;
import com.kunlong.service.LoginContext;
import com.kunlong.service.LoginSsoService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginSsoServiceImpl implements LoginSsoService {



    @Override
    public int addLoginSso(LoginSso loginSso) {

        SqlSession session = LoginContext.sqlSessionBuilder.getSession_tasklog(true);

        try {
            LoginSsoMapper tnMapper = session.getMapper(LoginSsoMapper.class);
            return tnMapper.insertSelective(loginSso);
        } finally {
            session.close();
        }

    }

    @Override
    public List<LoginSso> selectByExample(LoginSsoExample example) {

        SqlSession session = LoginContext.sqlSessionBuilder.getSession_tasklog(true);
        try {
            LoginSsoMapper tnMapper = session.getMapper(LoginSsoMapper.class);
            return tnMapper.selectByExample(example);
        } finally {
            session.close();
        }

    }

    @Override
    public int deleteByExample(LoginSsoExample example) {

        SqlSession session = LoginContext.sqlSessionBuilder.getSession_tasklog(true);

        try {
            LoginSsoMapper tnMapper = session.getMapper(LoginSsoMapper.class);
            return tnMapper.deleteByExample(example);
        } finally {
            session.close();
        }

    }


    @Override
    public int updateByPrimaryKeySelective(LoginSso record) {
        SqlSession session = LoginContext.sqlSessionBuilder.getSession_tasklog(true);

        try {
            LoginSsoMapper tnMapper = session.getMapper(LoginSsoMapper.class);
            return tnMapper.updateByPrimaryKeySelective(record);
        } finally {
            session.close();
        }

    }

    @Override
    public int insertSelective(LoginSso record) {
        SqlSession session = LoginContext.sqlSessionBuilder.getSession_tasklog(true);

        try {
            LoginSsoMapper tnMapper = session.getMapper(LoginSsoMapper.class);
            return tnMapper.insertSelective(record);
        } finally {
            session.close();
        }

    }


}
