package com.kunlong.dubbo.api.provider;

import app.support.query.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.kunlong.dubbo.sys.dto.queryParam.SysResourceQueryDTO;
import com.kunlong.dubbo.sys.model.SysResourceDTO;
import com.kunlong.dubbo.sys.model.SysResourceGroupDTO;
import com.kunlong.dubbo.sys.service.SysResourceApiService;
import com.kunlong.core.util.BeanMapper;
import com.kunlong.dubbo.api.DTFactory;
import com.kunlong.dubbo.api.transformer.SysResourceGroupTransformer;
import com.kunlong.dubbo.api.transformer.SysResourceTransformer;
import com.kunlong.sys.domain.SysResource;
import com.kunlong.sys.domain.SysResourceGroup;
import com.kunlong.sys.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${dubbo.service.version}")
public class SysResourceApiProvider implements SysResourceApiService {

	@Autowired
	private SysResourceService service;
	private static final SysResourceTransformer T = DTFactory.getInstance(SysResourceTransformer.class);
	
	@Override
	public PageResult<SysResourceDTO> query(SysResourceQueryDTO qpDTO) {
		SysResource.QueryParam qp = BeanMapper.getInstance().map(qpDTO, SysResource.QueryParam.class);
		List<SysResource> list = this.service.findByQueryParam(qp);
		long total = this.service.countByQueryParam(qp);
		
		PageResult<SysResourceDTO> page = new PageResult<SysResourceDTO>();
		page.setData(T.produces(list));
		page.setTotal(total);
		return page;
	}

	@Override
	public List<SysResourceGroupDTO> findAllResources() {
		List<SysResourceGroup> list = this.service.queryGroupAndResources();
		return DTFactory.getInstance(SysResourceGroupTransformer.class).produces(list);
	}

}
