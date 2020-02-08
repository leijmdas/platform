package com.kunlong.platform.dubbo.api.transformer;

import com.kunlong.platform.dubbo.api.ITransformer;
import com.kunlong.dubbo.sys.model.SysRoleDTO;
import com.kunlong.sys.domain.SysRole;
import com.kunlong.core.util.BeanMapper;

public class SysRoleTransformer implements ITransformer<SysRole,SysRoleDTO>{
	public static final SysRoleTransformer INSTANCE = new SysRoleTransformer();
	public SysRoleDTO produce(SysRole t) {
		return BeanMapper.getInstance().map(t, SysRoleDTO.class);
	}
	@Override
	public SysRole consume(SysRoleDTO r) {
		return BeanMapper.getInstance().map(r, SysRole.class);
	}
	
}
