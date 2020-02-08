package com.kunlong.dubbo.api.transformer;

import com.kunlong.dubbo.sys.model.SysJobGroupDTO;
import com.kunlong.core.util.BeanMapper;
import com.kunlong.dubbo.api.ITransformer;
import com.kunlong.sys.domain.SysJobGroup;

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
