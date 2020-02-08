package com.kunlong.dubbo.api.transformer;

import com.kunlong.dubbo.sys.model.SysUserDTO;
import com.kunlong.core.util.BeanMapper;
import com.kunlong.dubbo.api.ITransformer;
import com.kunlong.sys.domain.SysUser;

public class SysUserTransformer implements ITransformer<SysUser,SysUserDTO>{
	public static final SysUserTransformer INSTANCE = new SysUserTransformer();
	public SysUserDTO produce(SysUser t) {
		return BeanMapper.getInstance().map(t, SysUserDTO.class);
	}
	@Override
	public SysUser consume(SysUserDTO r) {
		return BeanMapper.getInstance().map(r, SysUser.class);
	}
	
}
