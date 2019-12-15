package com.kunlong.sysuser.service.impl;

import com.kunlong.mybatis.SqlSessionBuilder;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.sysuser.dao.SysMenuMapper;
import com.kunlong.sysuser.dao.SysRestListMapper;
import com.kunlong.sysuser.dao.SysRoleRightMapper;
import com.kunlong.sysuser.model.LeftMenuModel;
import com.kunlong.sysuser.model.SysRestListSimpleModel;
import com.kunlong.sysuser.model.Sys_MenuModel;
import com.kunlong.sysuser.model.Sys_RestListModel;
import com.kunlong.sysuser.service.SysPowerService;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: com.kunlong.sysuser.service.impl
 * Author: ZCS
 * Date: Created in 2018/8/21 20:01
 */
public class SysPowerServiceImpl implements SysPowerService {
    private static SqlSessionBuilder sqlSessionBuilder=new SqlSessionBuilder();

    @Override
    public List<Sys_MenuModel> getMenuList() {

        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {
            SysMenuMapper sysMenuDao = ss.getMapper(SysMenuMapper.class);
            return sysMenuDao.getMenuList();
        }
    }

    @Override
    public List<Sys_RestListModel> getRestList() {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysRestListMapper restListDao = ss.getMapper(SysRestListMapper.class);
            return restListDao.getRestList();
        }
    }

    @Override
    public void addMenu(Sys_MenuModel sysMenuModel) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysMenuMapper sysMenuDao = ss.getMapper(SysMenuMapper.class);
            sysMenuDao.addMenu(sysMenuModel);
            ss.commit();
        }

    }

    @Override
    public void addRest(Sys_RestListModel sysRestListModel) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {
            SysRestListMapper restListDao = ss.getMapper(SysRestListMapper.class);
            restListDao.addRestList(sysRestListModel);
            ss.commit();
        }

    }

    @Override
    public void deleteMenuById(int menuId) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysMenuMapper sysMenuDao = ss.getMapper(SysMenuMapper.class);
            sysMenuDao.deleteMenuById(menuId);
            ss.commit();
        }

    }

    @Override
    public void deleteRestListById(int restId) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {
            SysRestListMapper restListDao = ss.getMapper(SysRestListMapper.class);

            restListDao.deleteRestListById(restId);
            ss.commit();
        }

    }

    @Override
    public void updateMenu(Sys_MenuModel sysMenuModel) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysMenuMapper sysMenuDao = ss.getMapper(SysMenuMapper.class);
            sysMenuDao.updateMenu(sysMenuModel);
            ss.commit();
        }
    }

    @Override
    public void updateRestList(Sys_RestListModel sysRestListModel) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysRestListMapper restListDao = ss.getMapper(SysRestListMapper.class);

            restListDao.updateRestList(sysRestListModel);

            ss.commit();
        }
    }

    @Override
    public Sys_MenuModel getMenuInfoById(int menuId) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysMenuMapper menuDao = ss.getMapper(SysMenuMapper.class);
            return menuDao.getMenuInfoById(menuId);
        }
    }

    @Override
    public Sys_RestListModel getRestListInfoById(int restId) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysRestListMapper sysRestListDao = ss.getMapper(SysRestListMapper.class);
            return sysRestListDao.getRestListInfoById(restId);
        }
    }

    @Override
    public List<Sys_MenuModel> getMenuToRole(int roleId) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysMenuMapper sysMenuDao = ss.getMapper(SysMenuMapper.class);
            List<Sys_MenuModel>  menuList = sysMenuDao.getMenuToRole(roleId);
            for(Sys_MenuModel menu : menuList){
                menu.setIsSelect(true);
            }
            return menuList;
        }
    }

    @Override
    public List<Sys_RestListModel> getRestToRole(int roleId) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysRestListMapper restListDao = ss.getMapper(SysRestListMapper.class);
            List<Sys_RestListModel> restList = restListDao.getRestToRole(roleId);
            for(Sys_RestListModel rest : restList){
                rest.setIsSelect(true);
            }
            return restList;
        }


    }

    @Override
    public List<Sys_MenuModel> queryMenuParentId(int pareneId) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysMenuMapper sysMenuDao = ss.getMapper(SysMenuMapper.class);
            return sysMenuDao.getMenuParentId(pareneId);

        }
    }

    @Override
    public List<Sys_RestListModel> queryRestParentId(int parentId) {

        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysRestListMapper restListDao = ss.getMapper(SysRestListMapper.class);

            return restListDao.getRestParentId(parentId);

        }
    }

    @Override
    public List<LeftMenuModel> queryNotButtonList(int userId) {

        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysMenuMapper sysMenuDao = ss.getMapper(SysMenuMapper.class);
            return sysMenuDao.queryNotButtonList(userId);

        }
    }

    @Override
    public void saveRoleMenuOrRest(String menuIds,int roleId,int type) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            //如果menuIds的长度为0 ,则直接清空该角色的权限
            if(menuIds.length() <= 0){
                deltetSysRoleRight(roleId,type);
            }else{
                Map<String,Object> map = new HashMap<>();
                String ids[] = menuIds.split(",");
                List<Integer> list = new ArrayList();
                for(int i = 0;i<ids.length;i++){
                    if(ids[i] != null && !ids[i].equals("") ){
                        list.add(Integer.parseInt(ids[i]));
                    }
                }
                SysRoleRightMapper roleRightDao = ss.getMapper(SysRoleRightMapper.class);
                //清除角色与菜单关系
                deltetSysRoleRight(roleId,type);
                //新增信息
                map.put("menuIdList",list);
                map.put("roleId",roleId);
                map.put("type",type);
                roleRightDao.addSysRoleRight(map);
                ss.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new KunlongError(KunlongError.CODE_FAIL);
        }
    }

    @Override
    public void saveRoleRest(Map<String, Object> map) {

    }

    @Override
    public void deltetSysRoleRight(int roleId, int type) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {

            SysRoleRightMapper roleRightDao = ss.getMapper(SysRoleRightMapper.class);
            roleRightDao.deltetSysRoleRight(roleId,type);
            ss.commit();
        }
    }

    @Override
    public List<SysRestListSimpleModel> getSimpleRestList() {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {


            SysRestListMapper sysRestListDao = ss.getMapper(SysRestListMapper.class);
            return sysRestListDao.getSimpleRestList();
        }
    }

}
