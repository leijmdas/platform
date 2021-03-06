package com.kunlong.platform.dubbo.api.transformer;

import com.kunlong.platform.dubbo.api.ITransformer;
import com.kunlong.dubbo.sys.model.SysResourceDTO;
import com.kunlong.sys.domain.SysResource;
import com.kunlong.core.util.BeanMapper;

public class SysResourceTransformer implements ITransformer<SysResource,SysResourceDTO>{
	public static final SysResourceTransformer INSTANCE = new SysResourceTransformer();
	public SysResourceDTO produce(SysResource t) {
		return BeanMapper.getInstance().map(t, SysResourceDTO.class);
	}
	@Override
	public SysResource consume(SysResourceDTO r) {
		return BeanMapper.getInstance().map(r, SysResource.class);
	}
	
}
