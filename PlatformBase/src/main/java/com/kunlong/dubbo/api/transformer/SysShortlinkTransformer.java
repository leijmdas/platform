package com.kunlong.dubbo.api.transformer;

import com.kunlong.dubbo.sys.model.SysShortlinkDTO;
import com.kunlong.core.util.BeanMapper;
import com.kunlong.dubbo.api.ITransformer;
import com.kunlong.sys.domain.SysShortlink;

public class SysShortlinkTransformer implements ITransformer<SysShortlink,SysShortlinkDTO>{
	public static final SysShortlinkTransformer INSTANCE = new SysShortlinkTransformer();
	public SysShortlinkDTO produce(SysShortlink t) {
		return BeanMapper.getInstance().map(t, SysShortlinkDTO.class);
	}
	@Override
	public SysShortlink consume(SysShortlinkDTO r) {
		return BeanMapper.getInstance().map(r, SysShortlink.class);
	}
	
}
