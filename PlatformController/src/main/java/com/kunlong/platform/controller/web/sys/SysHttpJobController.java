package com.kunlong.platform.controller.web.sys;


import app.support.query.PageResult;
import cn.integriti.center.api.dto.queryParam.SysHttpJobQueryDTO;
import cn.integriti.center.api.model.SysHttpJobDTO;
import cn.integriti.center.api.model.SysJobGroupDTO;
import cn.integriti.center.api.service.SysHttpJobApiService;
import cn.integriti.center.api.service.SysJobGroupApiService;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.util.ValueFieldHelper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 
 * 
 * @author 
 *
 */
@RequestMapping(ApiConstants.PREFIX_SYS+"/httpjob")
@Controller
public class SysHttpJobController  {

	//@Autowired
	@Reference(lazy = true, version = "${dubbo.service.version}")
	private SysHttpJobApiService sysHttpJobApiService;

	@Reference(lazy = true, version = "${dubbo.service.version}")
	private SysJobGroupApiService sysJobGroupApiService;

	/**
	 * 查询
	 * @param qp
	 * @return
	 */
	@PostMapping(value="query")
	public @ResponseBody
    PageResult<SysHttpJobDTO> query(@RequestBody SysHttpJobQueryDTO qp) {
		PageResult<SysHttpJobDTO> rs = this.sysHttpJobApiService.query(qp);
		this.fillValues(rs.getData());
		return rs;
	}
	private void fillValues(List<SysHttpJobDTO> items) {
		
		if(items == null || items.size()<1) return ;
		Set<Integer> groupIds = new HashSet<Integer>();			
		for(SysHttpJobDTO tmp:items) {
			groupIds.add(tmp.getGroupId());			
		}
		
		List<SysJobGroupDTO> jobGroups = sysJobGroupApiService.findByIds(new ArrayList<Integer>(groupIds));
		
		
		ValueFieldHelper.oneToOne(items, SysHttpJobDTO::getGroupId, jobGroups, SysJobGroupDTO::getId, SysHttpJobDTO::setSysJobGroup);
		
	}
	
	/**
	 * 保存r
	 * @param entitySysHttpJobController
	 * @return
	 */
	@PostMapping(value="save")
	public @ResponseBody
    Integer save(@RequestBody SysHttpJobDTO entity) {
		if(entity.getId() == null) {
			return this.sysHttpJobApiService.save(entity);
		}
		return this.sysHttpJobApiService.update(entity);
	}
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	@GetMapping(value="get/{id}")
	public @ResponseBody
    SysHttpJobDTO get(@PathVariable("id")Integer id) {
		return this.sysHttpJobApiService.findById(id);
	}
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	@PostMapping(value="delete/{id}")
	public @ResponseBody
    Boolean delete(@PathVariable("id")Integer id) {
		return this.sysHttpJobApiService.delete(id);
	}
	/**
	 * 暂停触发器
	 * @param id
	 */
	@PostMapping(value="pause/{id}")
	public @ResponseBody
    void pause(@PathVariable("id")Integer id) {
		sysHttpJobApiService.pause(id);
	}
	/**
	 * 恢复触发器
	 * @param id
	 */
	@PostMapping(value="resume/{id}")
	public @ResponseBody
    void resume(@PathVariable("id")Integer id) {
		sysHttpJobApiService.resume(id);
	}

}
