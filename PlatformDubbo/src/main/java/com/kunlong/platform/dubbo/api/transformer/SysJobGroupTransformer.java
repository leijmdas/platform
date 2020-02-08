package com.kunlong.platform.dubbo.api.transformer;

import com.kunlong.platform.dubbo.api.ITransformer;
import com.kunlong.dubbo.sys.model.SysJobGroupDTO;
import com.kunlong.sys.domain.SysJobGroup;
import com.kunlong.core.util.BeanMapper;

public class SysJobGroupTransformer implements ITransformer<SysJobGroup,SysJobGroupDTO>{
	public static final SysJobGroupTransformer INSTANCE = new SysJobGroupTransformer();
	public SysJobGroupDTO produce(SysJobGroup t) {
		return BeanMapper.getInstance().map(t, SysJobGroupDTO.class);
	}
	@Override
	public SysJobGroup consume(SysJobGroupDTO r) {
		return BeanMapper.getInstance().map(r, SysJobGroup.class);
	}
	
}
