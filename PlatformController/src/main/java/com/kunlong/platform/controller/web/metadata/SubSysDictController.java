package com.kunlong.platform.controller.web.metadata;

import app.support.query.PageResult;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.kunlong.platform.dao.SubsysDictMapper;
import com.kunlong.platform.domain.SubsysDict;
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
@RequestMapping(ApiConstants.PREFIX_SYS+"/metadata/subsysdict")
@Controller
public class SubSysDictController extends BaseController {

	@Autowired
	private SubsysDictMapper subsysDictMapper;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
	PageResult<SubsysDict> query() {

		List<SubsysDict> list = this.subsysDictMapper.findByQueryParam(new SubsysDict.QueryParam());
		PageResult<SubsysDict> pageResult = new PageResult<SubsysDict>();
		pageResult.setData(list);
		pageResult.setTotal(list.size());
		return pageResult;
	}

//	/**
//	 * 保存
//	 *
//	 * @param entity
//	 * @return
//	 */
//	@RequestMapping(value = "save", method = RequestMethod.POST)
//	public @ResponseBody
//    Integer save(@RequestBody SysUserDTO entity) {
//		entity.setCorpId(this.getCurrentCorpId());
//		return this.sysUserService.save(entity);
//	}
//
//
//
//	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
//	public @ResponseBody
//    SysUserDTO getUser(@PathVariable("id") Integer id) {
//		SysUserDTO tmp = this.sysUserService.findById(id);
//		return tmp;
//	}
//
//	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
//	public @ResponseBody
//    Boolean delete(@PathVariable("id") Integer id) {
//		this.sysUserService.delete(id);
//		return true;
//	}
//	/**
//	 * 分配角色
//	 * @param id
//	 * @param roleType
//	 * @param roleIds
//	 * @return
//	 */
//	@RequestMapping(value = "assignRoles/{roleType}/{id}", method = RequestMethod.POST)
//	public @ResponseBody
//    Boolean assignRoles(@PathVariable("id") Integer id, @PathVariable("roleType")Integer roleType, @RequestBody List<Integer> roleIds) {
//		this.sysUserService.assignRoles(id, roleType, roleIds);
//		return true;
//	}
//
//	@RequestMapping(value = "findByIds", method = RequestMethod.POST)
//	public @ResponseBody
//    Boolean findByUserIds(@RequestBody List<Integer> userIds) {
//		this.sysUserService.findByIds(userIds);
//		return true;
//	}
//	@RequestMapping(value = "findRoleIds/{id}", method = RequestMethod.GET)
//	public @ResponseBody
//    List<Integer> findRoleIds(@PathVariable("id") Integer id, @RequestParam("type")Integer type) {
//		return this.sysUserService.findRoleIds(id, type);
//	}
//	@RequestMapping(value = "findByUsername", method = RequestMethod.POST)
//	public @ResponseBody
//    SysUserDTO findByUsername(Integer corpId, String username) {
//		SysUserDTO su = this.sysUserService.findByUsername(corpId,username);
//		return su;
//	}
//
//	@RequestMapping(value="authorization",method = RequestMethod.POST)
//	public @ResponseBody
//    AuthorizationDTO authrozation() {
//		Integer userId = this.getCurrentUserId();
//		AuthorizationDTO az = this.sysUserService.getAuthorization(userId);
//		return az;
//	}
//	@RequestMapping(value="modifyPassword",method = RequestMethod.POST)
//	public @ResponseBody
//    Boolean modifyPassword(@RequestBody ModifyPasswordParamDTO param) {
//		this.sysUserService.modifyPass(this.getCurrentUserId(), param.getOldPass(), param.getNewPass());
//		return true;
//	}
//	public static class ModifyPasswordParamDTO {
//		private String newPass;
//		private String oldPass;
//		public String getNewPass() {
//			return newPass;
//		}
//		public void setNewPass(String newPass) {
//			this.newPass = newPass;
//		}
//		public String getOldPass() {
//			return oldPass;
//		}
//		public void setOldPass(String oldPass) {
//			this.oldPass = oldPass;
//		}
//
//	}
}
