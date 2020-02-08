package com.kunlong.dubbo.api.transformer;

import com.kunlong.dubbo.sys.model.SysResourceGroupDTO;
import com.kunlong.core.util.BeanMapper;
import com.kunlong.dubbo.api.ITransformer;
import com.kunlong.sys.domain.SysResourceGroup;

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
