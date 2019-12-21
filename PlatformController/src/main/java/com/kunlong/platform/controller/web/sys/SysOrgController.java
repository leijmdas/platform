package com.kunlong.platform.controller.web.sys;

import app.support.query.PageResult;
import app.support.tree.TreeNode;
import cn.integriti.center.api.dto.queryParam.SysOrgQueryDTO;
import cn.integriti.center.api.model.SysOrgDTO;
import cn.integriti.center.api.service.SysOrgApiService;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 企业
 * 
 * @author zz
 *
 */
@RequestMapping(ApiConstants.PREFIX_SYS+"/org")
@Controller
public class SysOrgController extends BaseController {

	//@Autowired
	@Reference(lazy = true, version = "${dubbo.service.version}")
	private SysOrgApiService service;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
    PageResult<SysOrgDTO> query(@RequestBody SysOrgQueryDTO qp) {
		if(qp.getParam() == null) {
			qp.setParam(new SysOrgDTO());
		}
		qp.getParam().setCorpId(this.getCurrentCorpId());
		return this.service.query(qp);
	}

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
    Integer save(@RequestBody SysOrgDTO entity) {
		entity.setCorpId(this.getCurrentCorpId());
		return this.service.save(entity);
	}

	
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public @ResponseBody
    SysOrgDTO get(@PathVariable("id") Integer id) {
		SysOrgDTO tmp = this.service.findById(id);
		return tmp;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public @ResponseBody
    Boolean delete(@PathVariable("id") Integer id) {
		this.service.delete(id);
		return true;
	}

	@RequestMapping(value = "tree", method = RequestMethod.GET)
	public @ResponseBody
    TreeNode<SysOrgDTO> tree() {
		TreeNode<SysOrgDTO> root = this.service.tree(this.getCurrentCorpId());
		return root;
	}

}
