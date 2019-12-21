package com.kunlong.platform.controller.web.sys;

import app.support.query.PageResult;
import cn.integriti.center.api.dto.queryParam.SysUserQueryDTO;
import cn.integriti.center.api.model.AuthorizationDTO;
import cn.integriti.center.api.model.SysUserDTO;
import cn.integriti.center.api.service.SysUserApiService;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业
 * 
 * @author zz
 *
 */
@RequestMapping(ApiConstants.PREFIX_SYS+"/user")
@Controller
public class SysUserController extends BaseController {

	//@Autowired
	//@Reference
	@Reference(lazy = true, version = "${dubbo.service.version}")
	private SysUserApiService sysUserService;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
    PageResult<SysUserDTO> query(@RequestBody SysUserQueryDTO qp) {
		if(qp.getParam() == null) {
			qp.setParam(new SysUserDTO());;
		}
		qp.getParam().setCorpId(this.getCurrentCorpId());
		return this.sysUserService.query(qp);
	}

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
    Integer save(@RequestBody SysUserDTO entity) {
		entity.setCorpId(this.getCurrentCorpId());
		return this.sysUserService.save(entity);
	}

	

	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public @ResponseBody
    SysUserDTO getUser(@PathVariable("id") Integer id) {
		SysUserDTO tmp = this.sysUserService.findById(id);
		return tmp;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public @ResponseBody
    Boolean delete(@PathVariable("id") Integer id) {
		this.sysUserService.delete(id);
		return true;
	}
	/**
	 * 分配角色
	 * @param id
	 * @param roleType
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "assignRoles/{roleType}/{id}", method = RequestMethod.POST)
	public @ResponseBody
    Boolean assignRoles(@PathVariable("id") Integer id, @PathVariable("roleType")Integer roleType, @RequestBody List<Integer> roleIds) {
		this.sysUserService.assignRoles(id, roleType, roleIds);
		return true;
	}
	
	@RequestMapping(value = "findByIds", method = RequestMethod.POST)
	public @ResponseBody
    Boolean findByUserIds(@RequestBody List<Integer> userIds) {
		this.sysUserService.findByIds(userIds);
		return true;
	}
	@RequestMapping(value = "findRoleIds/{id}", method = RequestMethod.GET)
	public @ResponseBody
    List<Integer> findRoleIds(@PathVariable("id") Integer id, @RequestParam("type")Integer type) {
		return this.sysUserService.findRoleIds(id, type);
	}
	@RequestMapping(value = "findByUsername", method = RequestMethod.POST)
	public @ResponseBody
    SysUserDTO findByUsername(Integer corpId, String username) {
		SysUserDTO su = this.sysUserService.findByUsername(corpId,username);
		return su;
	}

	@RequestMapping(value="authorization",method = RequestMethod.POST)
	public @ResponseBody
    AuthorizationDTO authrozation() {
		Integer userId = this.getCurrentUserId();
		AuthorizationDTO az = this.sysUserService.getAuthorization(userId);
		return az;
	}
	@RequestMapping(value="modifyPassword",method = RequestMethod.POST)
	public @ResponseBody
    Boolean modifyPassword(@RequestBody ModifyPasswordParamDTO param) {
		this.sysUserService.modifyPass(this.getCurrentUserId(), param.getOldPass(), param.getNewPass());
		return true;
	}
	public static class ModifyPasswordParamDTO {
		private String newPass;
		private String oldPass;
		public String getNewPass() {
			return newPass;
		}
		public void setNewPass(String newPass) {
			this.newPass = newPass;
		}
		public String getOldPass() {
			return oldPass;
		}
		public void setOldPass(String oldPass) {
			this.oldPass = oldPass;
		}
		
	}
}
