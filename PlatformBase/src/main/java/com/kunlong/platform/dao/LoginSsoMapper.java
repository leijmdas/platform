package com.kunlong.platform.dao;

import com.kunlong.platform.model.LoginSso;
import com.kunlong.platform.model.LoginSsoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginSsoMapper {
    long countByExample(LoginSsoExample example);

    int deleteByExample(LoginSsoExample example);

    int deleteByPrimaryKey(Integer ssoid);

    int insert(LoginSso record);

    int insertSelective(LoginSso record);

    List<LoginSso> selectByExample(LoginSsoExample example);

    LoginSso selectByPrimaryKey(Integer ssoid);
    List<LoginSso> selectList(Integer ssoid);

    int updateByExampleSelective(@Param("record") LoginSso record, @Param("example") LoginSsoExample example);

    int updateByExample(@Param("record") LoginSso record, @Param("example") LoginSsoExample example);

    int updateByPrimaryKeySelective(LoginSso record);

    int updateByPrimaryKey(LoginSso record);
}