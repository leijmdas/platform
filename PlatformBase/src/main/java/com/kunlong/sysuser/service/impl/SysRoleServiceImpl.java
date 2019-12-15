package com.kunlong.sysuser.service.impl;

import com.kunlong.sysuser.dao.SysRoleMapper;
import com.kunlong.sysuser.dao.SysUserRoleMapper;
import com.kunlong.sysuser.model.Sys_RoleModel;
import com.kunlong.sysuser.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: com.kunlong.sysuser.service.impl
 * Author: ljm
 * Date: Created in 2019/8/21 13:13
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<Sys_RoleModel> getSysRoleList(String roleName) {
        return sysRoleMapper.getSysRoleList(roleName);

    }

    @Override
    public void addRole(Sys_RoleModel sysRoleModel) {
        //新增角色信息
        sysRoleMapper.addRole(sysRoleModel);


    }

    @Override
    public void updateRole(Sys_RoleModel sysRoleModel) {

        sysRoleMapper.updateRole(sysRoleModel);

    }

    @Override
    public void deleteRole(int roleId) {
        sysRoleMapper.deleteRole(roleId);

    }

    @Override
    public List<Sys_RoleModel> getUserRoleList(int userId) {

        List<Sys_RoleModel> list = sysRoleMapper.getUserRoleList(userId);
        for (Sys_RoleModel model : list) {
            model.setIsSelect(true);
        }
        return list;

    }

    @Override
    public void addSysUserRole(String roleId, int userId, int createBy) {


        if (roleId.length() <= 0) {
            deleteSysUserRole(userId);
        } else {


            //先清除用户与角色关系
            deleteSysUserRole(userId);

            //新增用户与角色关系
            String ids[] = roleId.split(",");
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < ids.length; i++) {
                list.add(Integer.parseInt(ids[i]));
            }
            Map map = new HashMap<>();
            map.put("userId", userId);
            map.put("createBy", createBy);
            map.put("roleIdList", list);
            sysUserRoleMapper.addSysUserRole(map);

        }
    }

    @Override
    public void deleteSysUserRole(int userId) {
        sysUserRoleMapper.deleteSysUserRole(userId);

    }
}
