package com.kunlong.platform.util.support.impl;


import com.kunlong.platform.util.JsonUtil;
import com.kunlong.platform.util.RedisUtil;
import com.kunlong.platform.support.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {


	/**
	 * 返回用户ID
	 * 
	 * @param token
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public AuthToken getToken(String token) {
		String key = "token:" + token;

		String atObj = RedisUtil.getInstance().getValue(key);
		if (atObj == null) {
			return null;
		}
		Map<String, Object> map = (HashMap<String, Object>) JsonUtil.readValue(atObj, HashMap.class);
		Long createTime = ((Number) map.get("createTime")).longValue();
		Long expiresIn = ((Number) map.get("expiresIn")).longValue();

		if (System.currentTimeMillis() - createTime > expiresIn * 1000) {
			throw new RuntimeException("令牌已失效");
		}

		AuthToken at = new AuthToken();
		at.setCreateTime(createTime);
		at.setExpiresIn(expiresIn);
		at.setToken(token);
		return at;
	}

	@Override
	public AuthToken createToken(String businessKey) {
		return this.createToken(businessKey, TOKEN_TIMEOUT);
	}
	public AuthToken createToken(String businessKey,long timeoutSeconds) {
		
		AuthToken authToken = new AuthToken();
		authToken.setCreateTime(System.currentTimeMillis());
		authToken.setExpiresIn(timeoutSeconds);

		String tokenId = UUID.randomUUID().toString().replaceAll("-", "");
		authToken.setToken(tokenId);

		return authToken;
	}
	
}
