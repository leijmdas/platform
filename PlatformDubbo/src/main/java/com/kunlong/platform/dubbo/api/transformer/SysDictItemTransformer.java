package com.kunlong.platform.dubbo.api.transformer;

import com.kunlong.platform.dubbo.api.ITransformer;
import com.kunlong.dubbo.sys.model.SysDictItemDTO;
import com.kunlong.sys.domain.SysDictItem;
import com.kunlong.core.util.BeanMapper;

public class SysDictItemTransformer implements ITransformer<SysDictItem,SysDictItemDTO>{
	public static final SysDictItemTransformer INSTANCE = new SysDictItemTransformer();
	public SysDictItemDTO produce(SysDictItem t) {
		return BeanMapper.getInstance().map(t, SysDictItemDTO.class);
	}
	@Override
	public SysDictItem consume(SysDictItemDTO r) {
		return BeanMapper.getInstance().map(r, SysDictItem.class);
	}
	
}
