package com.kunlong.platform.controller.web.sys;


import app.support.query.PageResult;
import cn.integriti.center.api.dto.queryParam.SysDictQueryDTO;
import cn.integriti.center.api.model.SysDictDTO;
import cn.integriti.center.api.model.SysDictItemDTO;
import cn.integriti.center.api.service.SysDictApiService;
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
@RequestMapping(ApiConstants.PREFIX_SYS+"/dict")
@Controller
public class SysDictController extends BaseController {

	//@Autowired
	@Reference(lazy = true, version = "${dubbo.service.version}")
	private SysDictApiService service;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
    PageResult<SysDictDTO> query(@RequestBody SysDictQueryDTO qp) {
		if(qp.getParam() == null) {
			qp.setParam(new SysDictDTO());
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
	@RequestMapping(value = "saveItem", method = RequestMethod.POST)
	public @ResponseBody
    Integer saveItem(@RequestBody SysDictItemDTO entity) {
		return service.saveDictItem(entity);
	}

	@RequestMapping(value = "deleteItem/{id}", method = RequestMethod.POST)
	public @ResponseBody
    Boolean deleteItem(@PathVariable("id") Integer id) {
		this.service.deleteItem(id);
		return true;
	}
	
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public @ResponseBody
    SysDictDTO get(@PathVariable("id") Integer id) {
		SysDictDTO tmp = this.service.findWithItemsById(id);
		return tmp;
	}
	@RequestMapping(value = "findByCode", method = RequestMethod.GET)
	public @ResponseBody
    SysDictDTO findByCode(String code) {
		return this.service.findWithItemsByCode(this.getCurrentCorpId(), code);
	}

//	@RequestMapping(value = "queryByDictCode", method = RequestMethod.POST)
//	public @ResponseBody PageResult<SysDictDTO> queryByDictCode(@RequestBody SysDictQueryDTO qp) {
//		if(qp.getParam() == null) {
//			qp.setParam(new SysDictDTO());
//		}
//		return this.service.query(qp);
//	}
}
