package com.kunlong.dubbo.api.transformer;

import com.kunlong.dubbo.sys.model.SysRoleDTO;
import com.kunlong.core.util.BeanMapper;
import com.kunlong.dubbo.api.ITransformer;
import com.kunlong.sys.domain.SysRole;

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
