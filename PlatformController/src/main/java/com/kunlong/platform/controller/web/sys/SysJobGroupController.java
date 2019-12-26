package com.kunlong.platform.controller.web.sys;


import app.support.query.PageResult;
import cn.integriti.center.api.dto.queryParam.SysJobGroupQueryDTO;
import cn.integriti.center.api.model.SysJobGroupDTO;
import cn.integriti.center.api.service.SysJobGroupApiService;
import com.kunlong.platform.consts.ApiConstants;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * 
 * @author 
 *
 */
@RequestMapping(ApiConstants.PREFIX_SYS+"/jopgroup")
@Controller
public class SysJobGroupController  {

	@Reference(lazy = true, version = "${dubbo.service.version}")
	//@Autowired
	private SysJobGroupApiService SysJobGroupService;

	/**
	 * 查询
	 * @param qp
	 * @return
	 */
	@PostMapping(value="query")
	public @ResponseBody
    PageResult<SysJobGroupDTO> query(@RequestBody SysJobGroupQueryDTO qp) {
		PageResult<SysJobGroupDTO> rs = this.SysJobGroupService.query(qp);
		return rs;
	}
	
	/**
	 * 保存r
	 * @param SysJobGroupDTO
	 * @return
	 */
	@PostMapping(value="save")
	public @ResponseBody
    Integer save(@RequestBody SysJobGroupDTO entity) {
		if(entity.getId() == null) {
			return this.SysJobGroupService.save(entity);
		}
		return this.SysJobGroupService.update(entity);
	}
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	@GetMapping(value="get/{id}")
	public @ResponseBody
    SysJobGroupDTO get(@PathVariable("id")Integer id) {
		return this.SysJobGroupService.findById(id);
	}
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	@PostMapping(value="delete/{id}")
	public @ResponseBody
    Boolean delete(@PathVariable("id")Integer id) {
		return this.SysJobGroupService.delete(id);
	}

}
