package com.kunlong.platform.dubbo.api.transformer;

import com.kunlong.platform.dubbo.api.ITransformer;
import com.kunlong.dubbo.sys.model.SysResourceGroupDTO;
import com.kunlong.sys.domain.SysResourceGroup;
import com.kunlong.core.util.BeanMapper;

public class SysResourceGroupTransformer implements ITransformer<SysResourceGroup,SysResourceGroupDTO>{
	public static final SysResourceGroupTransformer INSTANCE = new SysResourceGroupTransformer();
	public SysResourceGroupDTO produce(SysResourceGroup t) {
		return BeanMapper.getInstance().map(t, SysResourceGroupDTO.class);
	}
	@Override
	public SysResourceGroup consume(SysResourceGroupDTO r) {
		return BeanMapper.getInstance().map(r, SysResourceGroup.class);
	}
	
}
