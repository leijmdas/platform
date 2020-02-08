package com.kunlong.platform.dubbo.api.transformer;

import com.kunlong.platform.dubbo.api.ITransformer;
import com.kunlong.dubbo.sys.model.SysShortlinkDTO;
import com.kunlong.sys.domain.SysShortlink;
import com.kunlong.core.util.BeanMapper;

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
