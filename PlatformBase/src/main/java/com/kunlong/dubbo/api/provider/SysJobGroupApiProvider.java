package com.kunlong.dubbo.api.provider;

import app.support.query.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.kunlong.dubbo.sys.dto.queryParam.SysJobGroupQueryDTO;
import com.kunlong.dubbo.sys.model.SysJobGroupDTO;
import com.kunlong.dubbo.sys.service.SysJobGroupApiService;
import com.kunlong.core.util.BeanMapper;
import com.kunlong.dubbo.api.DTFactory;
import com.kunlong.dubbo.api.transformer.SysJobGroupTransformer;
import com.kunlong.sys.domain.SysJobGroup;
import com.kunlong.sys.service.SysJobGroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service(version = "${dubbo.service.version}")
public class SysJobGroupApiProvider implements SysJobGroupApiService {

	@Autowired
	private SysJobGroupService service;
	
	private static final SysJobGroupTransformer T = DTFactory.getInstance(SysJobGroupTransformer.class);
	
	@Override
	public PageResult<SysJobGroupDTO> query(SysJobGroupQueryDTO qpDTO) {
		SysJobGroup.QueryParam qp = BeanMapper.getInstance().map(qpDTO, SysJobGroup.QueryParam.class);
		List<SysJobGroup> list = this.service.findByQueryParam(qp);
		long total = this.service.countByQueryParam(qp);
		
		PageResult<SysJobGroupDTO> page = new PageResult<SysJobGroupDTO>();
		page.setData(T.produces(list));
		page.setTotal(total);
		return page;
	}

	@Override
	public Integer save(SysJobGroupDTO entityDTO) {
		SysJobGroup entity = T.consume(entityDTO);
		entity.setCreateOn(new Date());
		if(entity.getId() == null) {
			this.service.save(entity);
		} else {
			SysJobGroup tmp = this.service.findById(entity.getId());
			BeanMapper.getInstance().map(entity, tmp);
			tmp.setUpdateOn(new Date());
			this.service.update(tmp);
		}
		return entity.getId();
	}

	@Override
	public SysJobGroupDTO findById(Integer id) {
		SysJobGroup tmp = this.service.findById(id);
		return T.produce(tmp);
	}

	@Override
	public boolean delete(Integer id) {
		this.service.deleteById(id);
		return true;
	}

	@Override
	public List<SysJobGroupDTO> findByIds(List<Integer> ids) {
		List<SysJobGroup> suList = this.service.findByIds(ids);
		return T.INSTANCE.produces(suList);
	}

	@Override
	public Integer update(SysJobGroupDTO entityDTO) {
		SysJobGroup entity = T.consume(entityDTO);
		SysJobGroup tmp = this.service.findById(entity.getId());
		BeanMapper.getInstance().map(entity, tmp);
		this.service.update(tmp);
		return entity.getId();
	}

	

	/*@Override
	public void update(SysJobGroupDTO entityDTO) {
		SysJobGroup entity = T.consume(entityDTO);
		this.service.update(entity);
	}*/

	/*@Override
	public List<Integer> findResourceIds(Integer id) {
		return this.service.findResourceIds(id);
	}*/

	/*@Override
	public void assignRoleResources(Integer id, List<Integer> resIds) {
		this.service.assignRoleResources(id, resIds);
	}*/
}
