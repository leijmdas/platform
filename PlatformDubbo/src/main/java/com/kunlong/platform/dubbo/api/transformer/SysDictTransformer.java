package com.kunlong.platform.dubbo.api.transformer;

import com.kunlong.platform.dubbo.api.ITransformer;
import com.kunlong.dubbo.sys.model.SysDictDTO;
import com.kunlong.sys.domain.SysDict;
import com.kunlong.core.util.BeanMapper;

public class SysDictTransformer implements ITransformer<SysDict,SysDictDTO>{
	public static final SysDictTransformer INSTANCE = new SysDictTransformer();
	public SysDictDTO produce(SysDict t) {
		return BeanMapper.getInstance().map(t, SysDictDTO.class);
	}
	@Override
	public SysDict consume(SysDictDTO r) {
		return BeanMapper.getInstance().map(r, SysDict.class);
	}
	
}
