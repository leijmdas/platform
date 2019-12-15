package com.kunlong.sysuser.service.impl;

import com.kunlong.sysuser.dao.SysMenuMapper;
import com.kunlong.sysuser.dao.SysRestListMapper;
import com.kunlong.sysuser.dao.SysRoleRightMapper;
import com.kunlong.sysuser.model.LeftMenuModel;
import com.kunlong.sysuser.model.SysRestListSimpleModel;
import com.kunlong.sysuser.model.Sys_MenuModel;
import com.kunlong.sysuser.model.Sys_RestListModel;
import com.kunlong.sysuser.service.SysPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: com.kunlong.sysuser.service.impl
 * Author: ZCS
 * Date: Created in 2018/8/21 20:01
 */
@Service
public class SysPowerServiceImpl implements SysPowerService {
    @Autowired
    SysMenuMapper sysMenuDao;//= ss.getMapper(SysMenuMapper.class);
    @Autowired
    SysRestListMapper restListDao;// = ss.getMapper(SysRestListMapper.class);
    @Autowired
    SysRoleRightMapper roleRightDao;//= ss.getMapper(SysRoleRightMapper.class);

    @Override
    public List<Sys_MenuModel> getMenuList() {

        return sysMenuDao.getMenuList();

    }

    @Override
    public List<Sys_RestListModel> getRestList() {
        return restListDao.getRestList();

    }

    @Override
    public void addMenu(Sys_MenuModel sysMenuModel) {
        sysMenuDao.addMenu(sysMenuModel);


    }

    @Override
    public void addRest(Sys_RestListModel sysRestListModel) {
        restListDao.addRestList(sysRestListModel);


    }

    @Override
    public void deleteMenuById(int menuId) {
        sysMenuDao.deleteMenuById(menuId);


    }

    @Override
    public void deleteRestListById(int restId) {
        restListDao.deleteRestListById(restId);


    }

    @Override
    public void updateMenu(Sys_MenuModel sysMenuModel) {
        sysMenuDao.updateMenu(sysMenuModel);

    }

    @Override
    public void updateRestList(Sys_RestListModel sysRestListModel) {
        restListDao.updateRestList(sysRestListModel);

    }

    @Override
    public Sys_MenuModel getMenuInfoById(int menuId) {
        return sysMenuDao.getMenuInfoById(menuId);

    }

    @Override
    public Sys_RestListModel getRestListInfoById(int restId) {
        return restListDao.getRestListInfoById(restId);

    }

    @Override
    public List<Sys_MenuModel> getMenuToRole(int roleId) {
        List<Sys_MenuModel> menuList = sysMenuDao.getMenuToRole(roleId);
        for (Sys_MenuModel menu : menuList) {
            menu.setIsSelect(true);
        }
        return menuList;

    }

    @Override
    public List<Sys_RestListModel> getRestToRole(int roleId) {
        List<Sys_RestListModel> restList = restListDao.getRestToRole(roleId);
        for (Sys_RestListModel rest : restList) {
            rest.setIsSelect(true);
        }
        return restList;


    }

    @Override
    public List<Sys_MenuModel> queryMenuParentId(int pareneId) {
        return sysMenuDao.getMenuParentId(pareneId);


    }

    @Override
    public List<Sys_RestListModel> queryRestParentId(int parentId) {
        return restListDao.getRestParentId(parentId);

    }

    @Override
    public List<LeftMenuModel> queryNotButtonList(int userId) {

        return sysMenuDao.queryNotButtonList(userId);

    }

    @Override
    public void saveRoleMenuOrRest(String menuIds, int roleId, int type) {
        //如果menuIds的长度为0 ,则直接清空该角色的权限
        if (menuIds.length() <= 0) {
            deltetSysRoleRight(roleId, type);
        } else {
            Map<String, Object> map = new HashMap<>();
            String ids[] = menuIds.split(",");
            List<Integer> list = new ArrayList();
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] != null && !ids[i].equals("")) {
                    list.add(Integer.parseInt(ids[i]));
                }
            }
            //清除角色与菜单关系
            deltetSysRoleRight(roleId, type);
            //新增信息
            map.put("menuIdList", list);
            map.put("roleId", roleId);
            map.put("type", type);
            roleRightDao.addSysRoleRight(map);

        }

    }

    @Override
    public void saveRoleRest(Map<String, Object> map) {

    }

    @Override
    public void deltetSysRoleRight(int roleId, int type) {
        roleRightDao.deltetSysRoleRight(roleId, type);

    }

    @Override
    public List<SysRestListSimpleModel> getSimpleRestList() {
        return restListDao.getSimpleRestList();

    }

}
