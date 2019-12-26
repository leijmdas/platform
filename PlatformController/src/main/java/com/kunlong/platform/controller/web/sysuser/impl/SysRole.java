package com.kunlong.platform.controller.web.sysuser.impl;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Sets;
import com.kunlong.platform.context.RestMessage.MsgHandler;
import com.kunlong.platform.context.RestMessage.MsgRequest;
import com.kunlong.platform.context.RestMessage.MsgResponse;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.platform.utils.KunlongUtils;
import com.kunlong.sysuser.model.Sys_RoleModel;
import com.kunlong.sysuser.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Package: com.kunlong.sysuser.sysuser.impl
 * Author: ZCS
 * Date: Created in 2018/8/22 14:11
 */
@Component
public class SysRole {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";
    @Autowired
    SysRoleService sysRoleService ;//= new SysRoleServiceImpl();

    public MsgResponse process(MsgHandler handler) {
        retcode = 0;
        retmsg = "成功";
        msgBody = null;

        MsgRequest req = handler.req;


        //查询角色列表
        if (req.cmd.equals("getRoleList")) {
            String roleName  = req.msgBody.getString("roleName");
            List<Sys_RoleModel> lst = sysRoleService.getSysRoleList(roleName);
            msgBody="{\"list\":"+ KunlongUtils.toJSONStringPretty(lst)+"}";
        }

        //添加角色列表
        else if(req.cmd.equals("addRole")){

            Sys_RoleModel sysRoleModel = req.msgBody.toJavaObject(req.msgBody,Sys_RoleModel.class);
           //后面从cookie中取
            sysRoleModel.setCreateBy(0);
            sysRoleService.addRole(sysRoleModel);

        }

        //获取用户所拥有的角色信息
        else if(req.cmd.equals("getUserRoleList")){

            int userId = req.msgBody.getInteger("userId");
            String roleName = req.msgBody.getString("roleName");

            //用户所拥有的角色
            List<Sys_RoleModel> userRoleList = sysRoleService.getUserRoleList(userId);

            //全部角色
            List<Sys_RoleModel> roleList = sysRoleService.getSysRoleList(roleName);

            JSONArray json = new JSONArray();
            Set<Sys_RoleModel> retSet= Sets.newLinkedHashSet(userRoleList);

            for(Sys_RoleModel roleModel :roleList){

                if(!retSet.contains(roleModel)){

                    retSet.add(roleModel);
                }

            }
            List<Sys_RoleModel> l=new LinkedList(retSet);
            Collections.sort(l);


            for(Sys_RoleModel roleModel :l){
                json.add(roleModel);
            }

            msgBody="{\"list\":"+json.toJSONString()+"}";
        }
        //删除角色
        else if(req.cmd.equals("deleteRoleById")){
            int roleId = req.msgBody.getInteger("roleId");
            sysRoleService.deleteRole(roleId);
        }

        //修改用户角色
        else if(req.cmd.equals("updateRoleInfo")){

            Sys_RoleModel roleModel = req.msgBody.toJavaObject(req.msgBody,Sys_RoleModel.class);
            sysRoleService.updateRole(roleModel);
        }

        //重置用户与角色关系
        else if(req.cmd.equals("saveUserRole")){
            String roleIds = req.msgBody.getString("roleId");
            int userId = req.msgBody.getInteger("userId");
            int createBy = req.msgBody.getInteger("createBy");

            sysRoleService.addSysUserRole(roleIds,userId,createBy);
        }

        else{
            throw new KunlongError(KunlongError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);


    }


}
