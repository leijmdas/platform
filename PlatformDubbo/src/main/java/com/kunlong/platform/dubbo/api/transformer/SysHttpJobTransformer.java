package com.kunlong.platform.dubbo.api.transformer;

import com.kunlong.platform.dubbo.api.ITransformer;
import com.kunlong.dubbo.sys.model.SysHttpJobDTO;
import com.kunlong.sys.domain.SysHttpJob;
import com.kunlong.core.util.BeanMapper;

public class SysHttpJobTransformer implements ITransformer<SysHttpJob,SysHttpJobDTO>{
	public static final SysHttpJobTransformer INSTANCE = new SysHttpJobTransformer();
	public SysHttpJobDTO produce(SysHttpJob t) {
		return BeanMapper.getInstance().map(t, SysHttpJobDTO.class);
	}
	@Override
	public SysHttpJob consume(SysHttpJobDTO r) {
		return BeanMapper.getInstance().map(r, SysHttpJob.class);
	}
	
}
