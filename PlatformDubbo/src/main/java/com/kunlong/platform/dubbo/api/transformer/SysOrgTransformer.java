package com.kunlong.platform.dubbo.api.transformer;

import com.kunlong.platform.dubbo.api.ITransformer;
import com.kunlong.dubbo.sys.model.SysOrgDTO;
import com.kunlong.sys.domain.SysOrg;
import com.kunlong.core.util.BeanMapper;

public class SysOrgTransformer implements ITransformer<SysOrg,SysOrgDTO>{
	public static final SysOrgTransformer INSTANCE = new SysOrgTransformer();
	public SysOrgDTO produce(SysOrg t) {
		return BeanMapper.getInstance().map(t, SysOrgDTO.class);
	}
	@Override
	public SysOrg consume(SysOrgDTO r) {
		return BeanMapper.getInstance().map(r, SysOrg.class);
	}
	
}
