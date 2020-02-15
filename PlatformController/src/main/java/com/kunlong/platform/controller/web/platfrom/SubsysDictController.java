package com.kunlong.platform.controller.web.platfrom;

import app.support.query.PageResult;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.kunlong.platform.dao.SubsysDictMapper;
import com.kunlong.platform.domain.SubsysDict;
import com.kunlong.platform.service.SubsysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * 
 * @author ljm
 *
 */
@RequestMapping(ApiConstants.PREFIX_SYS+"/subsysdict")
@Controller
public class SubsysDictController extends BaseController {

	@Autowired
	private SubsysDictService subsysDictService;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
	PageResult<SubsysDict> query() {
		SubsysDict.QueryParam queryParam=new SubsysDict.QueryParam();
		queryParam.setLimit(-1);
		List<SubsysDict> list = this.subsysDictService.findByQueryParam( queryParam );
		PageResult<SubsysDict> pageResult = new PageResult<SubsysDict>();
		pageResult.setData(list);
		pageResult.setTotal(list.size());
		return pageResult;
	}

}
