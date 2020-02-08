package com.kunlong.dubbo.api.provider;

import app.support.query.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.kunlong.dubbo.sys.dto.queryParam.SysHttpJobQueryDTO;
import com.kunlong.dubbo.sys.model.SysHttpJobDTO;
import com.kunlong.dubbo.sys.service.SysHttpJobApiService;
import com.kunlong.core.util.BeanMapper;
import com.kunlong.dubbo.api.DTFactory;
import com.kunlong.dubbo.api.transformer.SysHttpJobTransformer;
import com.kunlong.sys.domain.SysHttpJob;
import com.kunlong.sys.service.SysHttpJobService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${dubbo.service.version}")
public class SysHttpJobApiProvider implements SysHttpJobApiService {

	@Autowired
	private SysHttpJobService service;
	
	private static final SysHttpJobTransformer T = DTFactory.getInstance(SysHttpJobTransformer.class);
	
	@Override
	public PageResult<SysHttpJobDTO> query(SysHttpJobQueryDTO qpDTO) {
		SysHttpJob.QueryParam qp = BeanMapper.getInstance().map(qpDTO, SysHttpJob.QueryParam.class);
		List<SysHttpJob> list = this.service.findByQueryParam(qp);
		long total = this.service.countByQueryParam(qp);
		
		PageResult<SysHttpJobDTO> page = new PageResult<SysHttpJobDTO>();
		page.setData(T.produces(list));
		page.setTotal(total);
		return page;
	}

	@Override
	public Integer save(SysHttpJobDTO entityDTO) {
		SysHttpJob entity = T.consume(entityDTO);
		if(entity.getId() == null) {
			this.service.save(entity);
		} else {
			SysHttpJob tmp = this.service.findById(entity.getId());
			BeanMapper.getInstance().map(entity, tmp);
			this.service.update(tmp);
		}
		return entity.getId();
	}

	@Override
	public SysHttpJobDTO findById(Integer id) {
		SysHttpJob tmp = this.service.findById(id);
		return T.produce(tmp);
	}

	@Override
	public boolean delete(Integer id) {
		this.service.deleteById(id);
		return true;
	}

	@Override
	public List<SysHttpJobDTO> findByIds(List<Integer> ids) {
		List<SysHttpJob> suList = this.service.findByIds(ids);
		return T.produces(suList);
	}

	@Override
	public Integer update(SysHttpJobDTO entityDTO) {
		SysHttpJob entity = T.consume(entityDTO);
		SysHttpJob tmp = this.service.findById(entity.getId());
		BeanMapper.getInstance().map(entity, tmp);
		this.service.update(tmp);
		return entity.getId();
	}


	@Override
	public Integer pause(Integer id) {
		this.service.pause(id);
		return 1;
	}

	@Override
	public Integer resume(Integer id) {
		this.service.resume(id);
		return 1;
	}

	

	
}
