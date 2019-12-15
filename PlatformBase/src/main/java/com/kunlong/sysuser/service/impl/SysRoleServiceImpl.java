package com.kunlong.sysuser.service.impl;

import com.kunlong.mybatis.SqlSessionBuilder;
import com.kunlong.sysuser.dao.SysRoleMapper;
import com.kunlong.sysuser.dao.SysUserRoleMapper;
import com.kunlong.sysuser.model.Sys_RoleModel;
import com.kunlong.sysuser.service.SysRoleService;
import org.apache.ibatis.session.SqlSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: com.kunlong.sysuser.service.impl
 * Author: ZCS
 * Date: Created in 2018/8/21 13:13
 */
public class SysRoleServiceImpl implements SysRoleService {
    static SqlSessionBuilder sqlSessionBuilder=new SqlSessionBuilder();

    @Override
    public List<Sys_RoleModel> getSysRoleList(String roleName) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysRoleMapper roleDao = ss.getMapper(SysRoleMapper.class);
            return roleDao.getSysRoleList(roleName);
        }
    }

    @Override
    public void addRole(Sys_RoleModel sysRoleModel) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysRoleMapper roleDao = ss.getMapper(SysRoleMapper.class);
            //新增角色信息
            roleDao.addRole(sysRoleModel);
            ss.commit();
        }
    }

    @Override
    public void updateRole(Sys_RoleModel sysRoleModel) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysRoleMapper roleDao = ss.getMapper(SysRoleMapper.class);

            roleDao.updateRole(sysRoleModel);
            ss.commit();
        }
    }

    @Override
    public void deleteRole(int roleId) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {
            SysRoleMapper roleDao = ss.getMapper(SysRoleMapper.class);
            roleDao.deleteRole(roleId);
        }
    }

    @Override
    public List<Sys_RoleModel> getUserRoleList(int userId) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysRoleMapper roleDao = ss.getMapper(SysRoleMapper.class);
            List<Sys_RoleModel>  list = roleDao.getUserRoleList(userId);
            for(Sys_RoleModel model:list){
                model.setIsSelect(true);
            }
            return list;
        }

    }

    @Override
    public void addSysUserRole(String roleId,int userId,int createBy) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {


            if(roleId.length() <= 0){
                deleteSysUserRole(userId);
            }else{

                SysUserRoleMapper  userRoleDao = ss.getMapper(SysUserRoleMapper.class);

                //先清除用户与角色关系
                deleteSysUserRole(userId);

                //新增用户与角色关系
                String ids[] = roleId.split(",");
                List<Integer> list = new ArrayList<>();
                for(int i = 0;i<ids.length;i++){
                    list.add(Integer.parseInt(ids[i]));
                }
                Map map = new HashMap<>();
                map.put("userId",userId);
                map.put("createBy",createBy);
                map.put("roleIdList",list);
                userRoleDao.addSysUserRole(map);
                ss.commit();
            }
        }
    }

    @Override
    public void deleteSysUserRole(int userId) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysUserRoleMapper userRoleDao = ss.getMapper(SysUserRoleMapper.class);
            userRoleDao.deleteSysUserRole(userId);
            ss.commit();
        }
    }
}