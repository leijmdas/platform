package com.kunlong.dubbo.api.provider;

import app.support.query.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.kunlong.dubbo.sys.dto.queryParam.SysRoleQueryDTO;
import com.kunlong.dubbo.sys.model.SysRoleDTO;
import com.kunlong.dubbo.sys.service.SysRoleApiService;
import com.kunlong.core.util.BeanMapper;
import com.kunlong.dubbo.api.DTFactory;
import com.kunlong.dubbo.api.transformer.SysRoleTransformer;
import com.kunlong.sys.domain.SysRole;
import com.kunlong.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${dubbo.service.version}")
public class SysRoleApiProvider implements SysRoleApiService {

	@Autowired
	private SysRoleService service;
	
	private static final SysRoleTransformer T = DTFactory.getInstance(SysRoleTransformer.class);
	
	@Override
	public PageResult<SysRoleDTO> query(SysRoleQueryDTO qpDTO) {
		SysRole.QueryParam qp = BeanMapper.getInstance().map(qpDTO, SysRole.QueryParam.class);
		List<SysRole> list = this.service.findByQueryParam(qp);
		long total = this.service.countByQueryParam(qp);
		
		PageResult<SysRoleDTO> page = new PageResult<SysRoleDTO>();
		page.setData(T.produces(list));
		page.setTotal(total);
		return page;
	}

	@Override
	public Integer save(SysRoleDTO entityDTO) {
		SysRole entity = T.consume(entityDTO);
		if(entity.getId() == null) {
			this.service.save(entity);
		} else {
			SysRole tmp = this.service.findById(entity.getId());
			BeanMapper.getInstance().map(entity, tmp);
			this.service.update(tmp);
		}
		return entity.getId();
	}

	@Override
	public SysRoleDTO findById(Integer id) {
		SysRole tmp = this.service.findById(id);
		return T.produce(tmp);
	}

	@Override
	public void delete(Integer id) {
		this.service.deleteById(id);
	}

	@Override
	public List<SysRoleDTO> findByIds(List<Integer> ids) {
		List<SysRole> suList = this.service.findByIds(ids);
		return T.produces(suList);
	}

	@Override
	public List<Integer> findResourceIds(Integer id) {
		return this.service.findResourceIds(id);
	}

	@Override
	public void assignRoleResources(Integer id, List<Integer> resIds) {
		this.service.assignRoleResources(id, resIds);
	}
}
