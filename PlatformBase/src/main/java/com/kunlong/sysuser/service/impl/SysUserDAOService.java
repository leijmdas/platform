package com.kunlong.sysuser.service.impl;

import com.kunlong.sysuser.dao.SysUserMapper;
import com.kunlong.sysuser.model.SysUserModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class SysUserDAOService implements SysUserMapper {
    @Autowired
    protected SysUserMapper sysUserMapper;

     @Override
    public List<SysUserModel> getSysUserList(Map<String, Object> map) {

            return sysUserMapper.getSysUserList(map);

    }

    @Override
    public void addUserInfo(SysUserModel sysUserModel) {

          sysUserMapper.addUserInfo(sysUserModel);

    }

    @Override
    public void deleteUserbyId(int userId) {
          sysUserMapper.deleteUserbyId(userId);

    }

    @Override
    public void updateSysUserInfo(SysUserModel sysUserModel) {
          sysUserMapper.updateSysUserInfo(sysUserModel);

    }

    @Override
    public SysUserModel getSysUserInfoById(int userId) {

          return sysUserMapper.getSysUserInfoById(userId);

    }

    @Override
    public void updatePassword(String newPwd, int userId) {
        sysUserMapper.updatePassword(newPwd,userId);

    }



    @Override
    public void updateStatus(Map<String, Object> map) {
             sysUserMapper.updateStatus(map);

    }

    @Override
    public List<Integer> getUserIdByRoleId(int roleId) {
        return sysUserMapper.getUserIdByRoleId(roleId);
    }

    @Override
    public SysUserModel getUserByUserName(String userName) {
        return sysUserMapper.getUserByUserName(userName);
    }

    @Override
    public SysUserModel getUserByMobile(String mobile) {
        return sysUserMapper.getUserByMobile(mobile);
    }

    SysUserModel getUserByUserNameModel(String userName) {

           return sysUserMapper.getUserByUserName(userName);

    }

    SysUserModel getUserByMobileModel(String mobile) {

             return sysUserMapper.getUserByMobile(mobile);

    }

}
