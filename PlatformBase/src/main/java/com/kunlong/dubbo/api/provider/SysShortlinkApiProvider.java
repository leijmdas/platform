package com.kunlong.dubbo.api.provider;

import app.support.query.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.kunlong.dubbo.sys.dto.queryParam.SysShortlinkQueryDTO;
import com.kunlong.dubbo.sys.model.SysShortlinkDTO;
import com.kunlong.dubbo.sys.service.SysShortlinkApiService;
import com.kunlong.core.util.BeanMapper;
import com.kunlong.dubbo.api.DTFactory;
import com.kunlong.dubbo.api.transformer.SysShortlinkTransformer;
import com.kunlong.sys.domain.SysShortlink;
import com.kunlong.sys.service.SysShortlinkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${dubbo.service.version}")
public class SysShortlinkApiProvider implements SysShortlinkApiService {

	@Autowired
	private SysShortlinkService service;
	
	private static final SysShortlinkTransformer T = DTFactory.getInstance(SysShortlinkTransformer.class);
	
	@Override
	public PageResult<SysShortlinkDTO> query(SysShortlinkQueryDTO qpDTO) {
		SysShortlink.QueryParam qp = BeanMapper.getInstance().map(qpDTO, SysShortlink.QueryParam.class);
		
		List<SysShortlink> list = this.service.findByQueryParam(qp);
		long total = this.service.countByQueryParam(qp);
		
		PageResult<SysShortlinkDTO> page = new PageResult<SysShortlinkDTO>();
		page.setData(T.produces(list));
		page.setTotal(total);
		return page;
	}

	@Override
	public Integer save(SysShortlinkDTO entityDTO) {
		SysShortlink entity = T.consume(entityDTO);
		if(entity.getId() == null) {
			this.service.save(entity);
		} else {
			SysShortlink tmp = this.service.findById(entity.getId());
			BeanMapper.getInstance().map(entity, tmp);
			this.service.update(tmp);
		}
		return entity.getId();
	}

	@Override
	public SysShortlinkDTO findById(Integer id) {
		SysShortlink tmp = this.service.findById(id);
		return T.produce(tmp);
	}

	@Override
	public void delete(Integer id) {
		this.service.deleteById(id);
	}

	@Override
	public SysShortlinkDTO findByCode(String code) {
		SysShortlink tmp = this.service.findByCode(code);
		return T.produce(tmp);
	}
}
